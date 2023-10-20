// package com.sharesmiles.service;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.redis.core.DefaultTypedTuple;
// import org.springframework.data.redis.core.RedisTemplate;
// import org.springframework.data.redis.core.ZSetOperations;
// import org.springframework.scheduling.annotation.EnableScheduling;
// import org.springframework.scheduling.annotation.Scheduled;
// import org.springframework.stereotype.Service;

// import com.sharesmiles.model.Post;
// import com.sharesmiles.repository.PostRepository;

// import java.util.Collections;
// import java.util.List;
// import java.util.Optional;
// import java.util.Set;
// import java.util.stream.Collectors;

// // 为什么Redis存储的是Post的id而不是Post本身?
// // 1. 分离关注点：PostRankingService处理与帖子热度和排名相关的功能，并不关心帖子的实际功能，
// //              获取Post需要访问数据库，不该是它的职责。将两针分开使得每个服务更简单明确。
// // 2. 性能：     从Redis检索ID列表比从数据中检索完整的帖子对象列表更快。
// // 3. 灵活性：   返回ID列表更有灵活性，比如只想要其中几个帖子的详情等。


// @Service // Indicates that this is a Spring service component
// @EnableScheduling
// public class PostRankingService {

//     // Key used in Redis to store the heat ranking of posts
//     private static final String HEAT_RANKING_KEY = "posts:heatRanking";

//     @Autowired // Spring's auto-injection, to inject the RedisTemplate instance into this class
//     private RedisTemplate<String, String> redisTemplate;

//     @Autowired
//     private PostRepository postRepository;

//     // Increase the heat for a specific post
//     public void increaseHeat(Long postId, double heatDelta) {
//         // Increment the score (heat) for the specified postId in Redis's Sorted Set (ZSet)
//         redisTemplate.opsForZSet().incrementScore(HEAT_RANKING_KEY, String.valueOf(postId), heatDelta);
//     }

//     // Retrieve the top N posts based on heat
//     public List<Long> getTopPostsByHeat() {
//         // Fetch the top N post IDs from Redis's Sorted Set (ZSet)
//         Set<String> postIds = redisTemplate.opsForZSet().reverseRange(HEAT_RANKING_KEY, 0, 20);
//         // Return an empty list if the result is null
//         if (postIds == null) return Collections.emptyList();
//         // Convert Set<String> to List<Long>
//         return postIds.stream().map(Long::valueOf).collect(Collectors.toList());
//     }

//     // Remove a specific post from the heat ranking
//     public void removePostFromRanking(Long postId) {
//         redisTemplate.opsForZSet().remove(HEAT_RANKING_KEY, String.valueOf(postId));
//         ensureSufficientRankingData();
//     }

//     // ensure that there are enough data in redis
//     private void ensureSufficientRankingData() {
//         long count = Optional.ofNullable(redisTemplate.opsForZSet().size(HEAT_RANKING_KEY)).orElse(0L);
//         if (count < 30) lazyLoadPosts();
//     }

//     // loading top 100 post from database
//     @Scheduled(cron = "0 0 1 * * ?")    
//     // 秒：0  分钟：0  小时：1  日期：*（任何日期） 月份：*（任何月份） 星期几：? （不指定）
//     private void lazyLoadPosts() {
//         List<Post> topPosts = postRepository.findTop100ByOrderByHeatDesc();
        
//         Set<ZSetOperations.TypedTuple<String>> postMap = topPosts.stream()
//         .map(post -> new DefaultTypedTuple<>(post.getPostId().toString(), (double) post.getHeat()))
//         .collect(Collectors.toSet());

//         redisTemplate.opsForZSet().add(HEAT_RANKING_KEY, postMap);
//     }
// }