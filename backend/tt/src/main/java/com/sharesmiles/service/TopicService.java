package com.sharesmiles.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sharesmiles.model.Topic;
import com.sharesmiles.repository.TopicRepository;

public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    public Topic createTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    public List<Topic> searchTopicByName(String keyword) {
        return topicRepository.findByTopicnameContainingIgnoreCaseOptional(keyword);
    }

}
