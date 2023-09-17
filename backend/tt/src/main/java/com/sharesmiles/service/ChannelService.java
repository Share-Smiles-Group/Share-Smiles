package com.sharesmiles.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.sharesmiles.model.Channel;
import com.sharesmiles.model.Message;
import com.sharesmiles.repository.ChannelRepository;
import com.sharesmiles.repository.MessageRepository;

public class ChannelService {
    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private MessageRepository messageRepository;
    
    public Channel create(Channel channel) {
        return channelRepository.save(channel);
    }

    public Channel getChannelById(Long channelId) {
        return channelRepository.findById(channelId).orElse(null);
    }

    public Message sendMessageToChannel(Long channelId, String content) {
        Channel channel = channelRepository.findById(channelId).orElse(null);
        // if (channel == null):
        Message message = new Message();
        message.setContent(content);
        message.setChannel(channel);
        return messageRepository.save(message);
    }

    public Message editMessage(Long messageId, String content) {
        Message message = messageRepository.findById(messageId).orElse(null);
        message.setContent(content);
        return messageRepository.save(message);
    }
}
