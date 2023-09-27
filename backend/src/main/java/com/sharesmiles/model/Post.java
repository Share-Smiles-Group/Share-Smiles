package com.sharesmiles.model;

import java.time.LocalDateTime;
// import java.util.List;

import javax.persistence.GenerationType;
// import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

// import javax.persistence.OneToMany;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long postId;
    private String postname;
    private Integer heat = 0;

    @Column(name = "createed_at")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Topic_id")
    private Topic topic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_id")
    private User creator;

    // @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    // private List<Comment> comments; 

    // Getters
    public Long getPostId() {
        return postId;
    }
    public String getPostname() {
        return postname;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public Topic getTopic() {
        return topic;
    }
    public Long getCreatorId() {
        return creator.getId();
    }
    public Integer getHeat() {
        return heat;
    }

    // Setters
    public void setPostId(Long postId) {
        this.postId = postId;
    }
    public void setPostname(String postname) {
        this.postname = postname;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public void setTopic(Topic topic) {
        this.topic = topic;
    }
    public void setHeat(Integer heat) {
        this.heat = heat;
    }
    
}