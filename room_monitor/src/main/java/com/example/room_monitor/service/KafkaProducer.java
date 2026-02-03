package com.example.room_monitor.service;

import com.example.room_monitor.events.RoomTempEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

import static com.example.room_monitor.config.KafkaTopicConfig.TEMPERATURE_TOPIC;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendRoomTemperature(RoomTempEvent event) {

        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(
                TEMPERATURE_TOPIC,
                event.getRoomId().toString(),
                event
        );

        future.whenComplete((result, throwable) -> {
                if (throwable == null) {
                    log.info("KafkaProducer :: sendRoomTemperature :: Room temperature sent successfully : {}", event);
                } else {
                    log.error("KafkaProducer :: sendRoomTemperature :: Unable to send room temperature");
                }
            }
        );

    }
}
