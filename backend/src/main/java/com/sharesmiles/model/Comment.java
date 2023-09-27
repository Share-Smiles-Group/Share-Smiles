package com.sharesmiles.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mid;
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // @ManyToOne(fetch = FetchType.LAZY)
    //      ManyToOne: message实体与Topic实体之间存在多对一的关系，多个消息可以属于同个频道
    //      fetch = FetchType.LAZY: 从数据库中检索message实体，Topic实体不会被立即加载
    // @JoinColumn(name = "Topic_id")
    //      定义了此关系在数据库中物理外键的名字，message表中的Topic_id是一个外键，
    //      该字段引用了Topic表的主键
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User sender;


     // Getters
    public Long getMid() {
        return mid;
    }
    public String getContent() {
        return content;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public Post getPost() {
        return post;
    }
    public Long getSenderId() {
        return sender.getId();
    }

    // Setters
    public void setMid(Long mid) {
        this.mid = mid;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public void setPost(Post post) {
        this.post = post;
    }
    public void setSender(User sender) {
        this.sender = sender;
    }
}