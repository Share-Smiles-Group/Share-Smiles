package com.sharesmiles.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sharesmiles.model.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
    List<Post> findByTopicIdOrderByHeatDesc(Long topicId);
}
