package com.sharesmiles.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sharesmiles.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
    Optional<Message> findById(Long mid);
}
