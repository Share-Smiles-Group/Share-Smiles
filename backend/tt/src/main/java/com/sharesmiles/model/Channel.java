package com.sharesmiles.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long cid;
    private String channelname;
    
    // @OneToMany: 一对多的关系，channel到message有多个关联的实例
    // mappedBy = "channel": 目标实体（message）中的“channel”字段表示对channel引用
    // 级连 cascade = CascadeType.ALL: 
    //      关联实体（message）的生命周期与当前实体（channel）的关系
    //      CascadeType.ALL: 任何对channel的操作（保存，更新，删除等）都将级连到关联的“Message”
    //      删除channel，所有message实体也将被删除
    // 延迟加载 fetch = FetchType.LAZY: 
    //      FetchType.LAZY: 延迟加载，在数据库中检索channel实体，关联message实体不被立即加载
    //      好处：性能优化，避免不必要的从数据库中加载大量数据
    @OneToMany(mappedBy = "channel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Message> messages = new ArrayList<>();

    // Getters
    public Long getCid() {
        return cid;
    }

    public String getChannelname() {
        return channelname;
    }

    public List<Message> getMessages() {
        return messages;
    }

    // Setters
    public void setCid(Long cid) {
        this.cid = cid;
    }

    public void setChannelname(String channelname) {
        this.channelname = channelname;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }    
}
