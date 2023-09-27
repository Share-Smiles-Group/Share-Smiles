package com.sharesmiles.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sharesmiles.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findById(Long mid);
    List<Comment> findByPostIdOrderByCreatedTimeDesc(Long postId);
}

