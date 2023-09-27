package com.sharesmiles.service;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.beans.factory.annotation.Autowired;

import com.sharesmiles.model.Comment;
import com.sharesmiles.repository.CommentRepository;

import javax.persistence.EntityNotFoundException;

public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    private boolean isValidComment(String content) {
        return !(content.length() < 1 || content.length() > 1000);       
    }

    public Comment commentCreate(Comment comment) {
        // 检查comment是否符合格式
        if (!isValidComment(comment.getContent()))
            throw new IllegalArgumentException("comment is not valid");
        return commentRepository.save(comment);
    }

    public Comment commentEdit(Long uid, Long mid, String newContent) {
        // 1. 检查comment是否符合格式
        if (!isValidComment(newContent))
            throw new IllegalArgumentException("Comment is not valid");
        
        // 检查mid对应的comment是否存在
        Comment comment = commentRepository.findById(mid).orElseThrow(() -> new EntityNotFoundException("comment not found"));

        // 检查用户是否在修改自己创建的comment
        if (!comment.getSenderId().equals(uid)) 
            throw new AccessDeniedException("User does not have permission to edit this comment");
            
        comment.setContent(newContent);
        return commentRepository.save(comment);
    }

}
