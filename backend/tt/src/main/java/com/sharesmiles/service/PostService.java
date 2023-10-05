package com.sharesmiles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.AccessDeniedException;

import com.sharesmiles.model.Comment;
import com.sharesmiles.model.Post;
import com.sharesmiles.repository.CommentRepository;
import com.sharesmiles.repository.PostRepository;
import com.sharesmiles.repository.TopicRepository;

import org.redisson.Redisson;
import org.redisson.api.RLock;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.EntityNotFoundException;

public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private PostRankingService postRankingService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private Redisson redisson;

    // 将频繁使用的post储存到内存
    @Autowired
    private RedisTemplate<String, Post> redisTemplate;

    // 储存访问次数的map
    // private Map<Long, Integer> postAccessCount = new HashMap<>();
    private Map<Long, Integer> postAccessCount = new ConcurrentHashMap<>();

    private boolean isValidPostName(String postName) {
        return !(postName.length() < 1 || postName.length() > 20);
    }

    public List<Post> getPostsByIds(List<Long> ids) {
        return postRepository.findByIdIn(ids);
    }

    public Post getPostById(Long postId) {
        //先从redis里面获取post
        Post post = redisTemplate.opsForValue().get(postId.toString());

        // 如果不存在于redis里，再从数据库里面获取
        if (post == null)  
            post = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post not found"));
        
        // 获取分布式锁
        RLock lock = redisson.getLock("postAccessCountLock:" + postId);
        lock.lock();
        try {
            // 使用Redis的原子操作来增加热度
            redisTemplate.opsForValue().increment("postAccessCount:" + postId, 1);
            
            // 在锁的保护下更新postAccessCount map
            postAccessCount.put(postId, postAccessCount.getOrDefault(postId, 0) + 1);
        } finally {
            // 释放锁
            lock.unlock(); 
        }
        return post;
    }

    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostIdOrderByCreatedTimeDesc(postId);
    }

    // 创建一个新的Post
    public Post createPost(Post post) {
        if (!isValidPostName(post.getPostname()))
            throw new IllegalArgumentException("Post name should between 1 and 20 characters");

        // 将该post加入到数据库中
        Post savedPost = postRepository.save(post);

        // 判断Post热度排行榜是否有100个，如果不超过20个就直接把新的post加入
        List<Long> topPosts = postRankingService.getTopPostsByHeat();
        if (topPosts.size() < 200) 
            postRankingService.increaseHeat(savedPost.getPostId(), 0);
        
        return savedPost;
    }

    // 删除一个Post
    public void deletePost(Long postId, Long userId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post not found"));
    
        if (!post.getCreatorId().equals(userId)) 
            throw new AccessDeniedException("User does not have permission to delete this post");
        
        postRepository.delete(post);

        // 从redis的热度排行榜里也删掉这个post
        postRankingService.removePostFromRanking(postId);
    }

    public List<Post> findPostByTopicSortedByHeat(Long topicId) {
        // 检查topicId是否有效
        if (!topicRepository.existsById(topicId))
            throw new EntityNotFoundException("topic not found");
        
        // 根据post的热度降序显示
        return postRepository.findByTopicIdOrderByHeatDesc(topicId);
    }
    

    private static final int SOME_THRESHOLD = 10;
    // 每一分钟执行一次
    @Scheduled(fixedRate = 60000)
    public void resetAccessCount() {
        for (Map.Entry<Long, Integer> entry : postAccessCount.entrySet()) {
            Long postId = entry.getKey();
            Integer count = entry.getValue();

            if (count >= SOME_THRESHOLD) {
                // 添加到缓存
                Post post = postRepository.findById(postId).orElse(null);
                if (post != null) {
                    String redisKey = "post: " + postId;
                    // 缓存过期策略
                    // 添加到Redis的时候, 设置了一个为期1天的过期时间。
                    redisTemplate.opsForValue().set(redisKey, post, Duration.ofDays(1));
                }
            }
        }
        postAccessCount.clear();
    }
}
