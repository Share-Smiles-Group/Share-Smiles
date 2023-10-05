package com.sharesmiles.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long topicId;
    private String topicName;

    // Getters
    public Long getCid() {
        return topicId;
    }
    public String getTopicname() {
        return topicName;
    }

    // Setters
    public void setCid(Long topicId) {
        this.topicId = topicId;
    }
    public void setTopicname(String Topicname) {
        this.topicName = Topicname;
    }
}
