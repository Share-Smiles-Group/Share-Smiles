package com.sharesmiles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.boot.autoconfigure.domain.EntityScan;
// import org.springframework.context.annotation.ComponentScan;
// import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
// @ComponentScan(basePackages = {"com.sharesmiles"})
// @EntityScan("com.sharesmiles.model")
// @EnableJpaRepositories(basePackages = {"com.sharesmiles.repository"})
public class ShareSmilesApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShareSmilesApplication.class, args);
    }
}

