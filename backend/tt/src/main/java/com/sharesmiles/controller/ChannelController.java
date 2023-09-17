package com.sharesmiles.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharesmiles.model.Channel;
import com.sharesmiles.model.Message;
import com.sharesmiles.service.ChannelService;

@RestController
@RequestMapping("/api/channel")
public class ChannelController {
    @Autowired
    private ChannelService channelService;

    @PostMapping("/create")
    public Channel create(@RequestBody Channel channel) {
        return channelService.create(channel);
    }

    @GetMapping("/{channelId}/messages")
    public List<Message> getMessagesByCid(@RequestBody Long cid) {
        Channel channel = channelService.getChannelById(cid);
        return channel.getMessages();
    }

    @PostMapping("/{channelId}/send")
    public ResponseEntity<Message> sendMessage(@PathVariable Long channelId, @RequestBody String content) {
        Message message = channelService.sendMessageToChannel(channelId, content);
        return ResponseEntity.ok(message);
    }
}
