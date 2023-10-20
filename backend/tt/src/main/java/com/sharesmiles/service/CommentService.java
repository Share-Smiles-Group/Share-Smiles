// package com.sharesmiles.service;

// import org.springframework.security.access.AccessDeniedException;
// import org.springframework.stereotype.Service;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.redis.core.RedisTemplate;

// import com.sharesmiles.model.Comment;
// import com.sharesmiles.repository.CommentRepository;

// import java.time.Duration;

// import javax.persistence.EntityNotFoundException;

// @Service
// public class CommentService {
//     @Autowired
//     private CommentRepository commentRepository;

//     @Autowired
//     private RedisTemplate<String, Comment> commentRedisTemplate;

//     @Autowired
//     private PostRankingService postRankingService;

//     // @Autowired
//     // private CommentKafkaProducer commentKafkaProducer;

//     private boolean isValidComment(String content) {
//         return !(content.length() < 1 || content.length() > 1000);       
//     }

//     public Comment getCommentById(Long commentId) {
//         // 先从缓存里获取comment
//         Comment comment = commentRedisTemplate.opsForValue().get("comment" + commentId);

//         // 如果缓存里没有评价，从数据库里面获取
//         if (comment == null) {
//             comment = commentRepository.findById(commentId).orElse(null);

//             // 把数据库中的评论数据储存在redis里
//             if (comment != null) {
//                 commentRedisTemplate.opsForValue().set("comment" + commentId, comment, Duration.ofDays(1));
//             }
//         }

//         return comment;
//     }

//     public Comment commentCreate(Comment comment) {
//         // 检查comment是否符合格式
//         if (!isValidComment(comment.getContent()))
//             throw new IllegalArgumentException("comment is not valid");
//         // 将comment加入到数据库中
//         Comment savedComment = commentRepository.save(comment);

//         // 为comment所在的post增加1点热度
//         // commentKafkaProducer.sendCommentForProcessing(savedComment);

//         Long postId = comment.getPost().getPostId();
//         postRankingService.increaseHeat(postId, 1);

//         return savedComment;
//     }

//     public Comment commentEdit(Long uid, Long mid, String newContent) {
//         // 1. 检查comment是否符合格式
//         if (!isValidComment(newContent))
//             throw new IllegalArgumentException("Comment is not valid");
        
//         // 检查mid对应的comment是否存在
//         Comment comment = commentRepository.findById(mid).orElseThrow(() -> new EntityNotFoundException("comment not found"));

//         // 检查用户是否在修改自己创建的comment
//         if (!comment.getSenderId().equals(uid)) 
//             throw new AccessDeniedException("User does not have permission to edit this comment");
            
//         comment.setContent(newContent);
//         return commentRepository.save(comment);
//     }

// }
