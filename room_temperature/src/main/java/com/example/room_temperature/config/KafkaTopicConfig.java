package com.example.room_temperature.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    public static final String TEMPERATURE_TOPIC = "temperature_topic";
    public static final String ALARM_TOPIC = "alarm_topic";

    @Bean
    public NewTopic alarmTopic(){
        return TopicBuilder
                .name(ALARM_TOPIC)
                .build();
    }
}
