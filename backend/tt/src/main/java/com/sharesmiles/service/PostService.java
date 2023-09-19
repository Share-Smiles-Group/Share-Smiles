package com.sharesmiles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;

import com.sharesmiles.model.Post;
import com.sharesmiles.repository.PostRepository;
import com.sharesmiles.repository.TopicRepository;

import java.util.List;

import javax.persistence.EntityNotFoundException;

public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TopicRepository topicRepository;

    private boolean isValidPost(String postName) {
        return !(postName.length() < 1 || postName.length() > 20);
    }
    // 创建一个新的Post
    public Post createPost(Post post) {
        if (!isValidPost(post.getPostname()))
            throw new IllegalArgumentException("Post name should between 1 and 20 characters");
            
        return postRepository.save(post);
    }

    // 删除一个Post
    public void deletePost(Long postId, Long userId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new EntityNotFoundException("Post not found"));
    
        if (!post.getCreatorId().equals(userId)) 
            throw new AccessDeniedException("User does not have permission to delete this post");
        
        postRepository.delete(post);
    }

    public List<Post> findPostByTopicSortedByHeat(Long topicId) {
        // 检查topicId是否有效
        if (!topicRepository.existsById(topicId))
            throw new EntityNotFoundException("topic not found");
        
        // 根据post的热度降序显示
        return postRepository.findByTopicIdOrderByHeatDesc(topicId);
    }

}
