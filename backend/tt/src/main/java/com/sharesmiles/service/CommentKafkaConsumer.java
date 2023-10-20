// package com.sharesmiles.service;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.kafka.annotation.KafkaListener;
// import org.springframework.stereotype.Service;

// import com.sharesmiles.model.Comment;

// @Service
// public class CommentKafkaConsumer {

//     @Autowired
//     private PostRankingService postRankingService;

//     @KafkaListener(topics = "commentTopic", groupId = "commentGroup")
//     public void consumeComment(Comment comment) {
//         Long postId = comment.getPost().getPostId();
//         postRankingService.increaseHeat(postId, 1);
//     }
// }

