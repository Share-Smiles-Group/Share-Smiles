package com.sharesmiles.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// 为什么Redis存储的是Post的id而不是Post本身?
// 1. 分离关注点：PostRankingService处理与帖子热度和排名相关的功能，并不关心帖子的实际功能，
//              获取Post需要访问数据库，不该是它的职责。将两针分开使得每个服务更简单明确。
// 2. 性能：     从Redis检索ID列表比从数据中检索完整的帖子对象列表更快。
// 3. 灵活性：   返回ID列表更有灵活性，比如只想要其中几个帖子的详情等。


@Service // Indicates that this is a Spring service component
public class PostRankingService {

    // Key used in Redis to store the heat ranking of posts
    private static final String HEAT_RANKING_KEY = "posts:heatRanking";

    @Autowired // Spring's auto-injection, to inject the RedisTemplate instance into this class
    private RedisTemplate<String, String> redisTemplate;

    // Increase the heat for a specific post
    public void increaseHeatWithOptimisticLock(Long postId, double heatDelta) {
        while(true) {
            // watch the heat ranking key
            redisTemplate.watch(HEAT_RANKING_KEY);

            // try to increase the heat
            redisTemplate.multi();
            redisTemplate.opsForZSet().incrementScore(HEAT_RANKING_KEY, String.valueOf(postId), heatDelta);
            List<Object> results = redisTemplate.exec();

            // if results is not null, it menas the operation was successful
            if (results != null && !results.isEmpty()) break;   
        }
    }

    // Retrieve the top N posts based on heat
    public List<Long> getTopPostsByHeat(int topN) {
        Set<String> postIds = redisTemplate.opsForZSet().reverseRange(HEAT_RANKING_KEY, 0, topN-1);
        if (postIds == null) return Collections.emptyList();
        return postIds.stream().map(Long::valueOf).collect(Collectors.toList());
    }

    // Remove a specific post from the heat ranking
    public void removePostFromRanking(Long postId) {
        redisTemplate.opsForZSet().remove(HEAT_RANKING_KEY, String.valueOf(postId));
    }

    // Get the ranking position of a specific post in terms of heat
    public Long getPostRank(Long postId) {
        return redisTemplate.opsForZSet().reverseRank(HEAT_RANKING_KEY, String.valueOf(postId));
    }
}