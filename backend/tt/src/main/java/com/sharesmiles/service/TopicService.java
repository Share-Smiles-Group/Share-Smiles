package com.sharesmiles.service;

import java.util.List;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.sharesmiles.model.Topic;
import com.sharesmiles.repository.TopicRepository;


public class TopicService {
    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private RedisTemplate<String, Topic> topicRedisTemplate;

    public Topic getTopicById(Long topicId) {
        // 从Redis里获取topic
        Topic topic = topicRedisTemplate.opsForValue().get("topic:" + topicId);

        // 如果redis没有topic数据
        if (topic == null) {
            topic = topicRepository.findById(topicId).orElse(null);

            // 如果数据库里有用户数据，储存到redis里面
            if (topic != null) {
                String redisKey = "topic:" + topicId;
                topicRedisTemplate.opsForValue().set(redisKey, topic, Duration.ofDays(1));
            }
        }

        return topic;
    }

    public Topic createTopic(Topic topic) {
        return topicRepository.save(topic);
    }

    public List<Topic> searchTopicByName(String keyword) {
        return topicRepository.findByTopicnameContainingIgnoreCaseOptional(keyword);
    }
}
