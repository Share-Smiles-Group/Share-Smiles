package com.sharesmiles.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sharesmiles.model.Topic;
import com.sharesmiles.service.TopicService;

@RestController
@RequestMapping("/api/topic")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @PostMapping("/create")
    public ResponseEntity<Topic> create(@RequestBody Topic topic) {
        Topic createdTopic = topicService.createTopic(topic);
        return new ResponseEntity<Topic>(createdTopic, HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Topic>> searchTopicByName(String keyword) {
        List<Topic> searchedTopics =  topicService.searchTopicByName(keyword);
        return new ResponseEntity<List<Topic>>(searchedTopics, HttpStatus.OK);
    }
}
