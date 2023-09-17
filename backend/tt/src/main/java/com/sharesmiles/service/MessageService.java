package com.sharesmiles.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.sharesmiles.repository.MessageRepository;

public class MessageService {
    @Autowired
    private MessageRepository messageRepository;


}
