// package com.sharesmiles.service;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.kafka.core.KafkaTemplate;
// import org.springframework.stereotype.Service;

// import com.sharesmiles.model.Comment;

// /**
//  * kafka的好处：
//  * 1. 吞吐量：kafka设计用于高吞吐量，能够处理每秒数十万或者数百万的消息，对于高数据量的应用程序非常有利
//  * 2. 持久性和日志储存：kafka以日志格式存储数据，并保留消息一段时间，如七天或三十天，适合长时间的数据储存
//  * 3. 分区和拓展性：kafka的主题可以分区储存在多个服务器上，为水平扩展提供了良好的支持。当需要处理更大数据量时，可以添加更多的节点
//  * 4. 有流处理能力：kafka与Kafka Streams和KSQL等流处理工具集成良好，允许实时处理和分析流数据
//  * 5. 多消费者：同一个消息可以被多个消费者组并行消费
//  * 6. 生态系统：Kafka有庞大的生态系统，与很多大数据工具都有良好的集成
//  */


// @Service
// public class CommentKafkaProducer {

//     @Autowired
//     private KafkaTemplate<String, Comment> kafkaTemplate;
//     private final String topicName = "commentTopic";

//     public void sendCommentForProcessing(Comment comment) {
//         kafkaTemplate.send(topicName, comment);
//     }
// }
