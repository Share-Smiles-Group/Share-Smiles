package com.sharesmiles.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mid;
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // @ManyToOne(fetch = FetchType.LAZY)
    //      ManyToOne: message实体与channel实体之间存在多对一的关系，多个消息可以属于同个频道
    //      fetch = FetchType.LAZY: 从数据库中检索message实体，channel实体不会被立即加载
    @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "channel_id")
    //      定义了此关系在数据库中物理外键的名字，message表中的channel_id是一个外键，
    //      该字段引用了channel表的主键
    @JoinColumn(name = "channel_id")
    private Channel channel;

    // Getters
    public Long getMid() {
        return mid;
    }
    public String getContent() {
        return content;
    }
    public Channel getChannel() {
        return channel;
    }

    // Setters
    public void setMid(Long mid) {
        this.mid = mid;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}