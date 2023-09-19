package com.sharesmiles.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sharesmiles.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long>{
    List<Topic> findByTopicnameContainingIgnoreCaseOptional(String Topicname);
}
