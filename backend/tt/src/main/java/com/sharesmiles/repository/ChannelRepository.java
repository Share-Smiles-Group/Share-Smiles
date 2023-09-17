package com.sharesmiles.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sharesmiles.model.Channel;

public interface ChannelRepository extends JpaRepository<Channel, Long>{
    Optional<Channel> findByChannelnameContaining(String channelname);
    
}
