package com.example.room_monitor.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    public static final String TEMPERATURE_TOPIC = "temperature_topic";

    @Bean
    public NewTopic temperatureTopic(){
        return TopicBuilder
                .name(TEMPERATURE_TOPIC)
                .build();
    }
}
