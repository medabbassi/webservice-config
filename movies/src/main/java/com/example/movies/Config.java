package com.example.movies;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class Config {

    @Bean
    public  NewTopic webDitrubutedTopic(){
        return TopicBuilder.name("movies-topic")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
