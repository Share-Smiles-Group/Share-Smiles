package com.sharesmiles.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

// import com.sharesmiles.model.Topic;
import com.sharesmiles.model.User;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, User> userRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, User> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        return template;
    }

    // @Bean 
    // public RedisTemplate<String, Topic> topicRedisTemplate(RedisConnectionFactory factory) {
    //     RedisTemplate<String, Topic> template = new RedisTemplate<>();
    //     template.setConnectionFactory(factory);
    //     return template;
    // }

}
