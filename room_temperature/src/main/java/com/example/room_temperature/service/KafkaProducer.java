package com.example.room_temperature.service;

import com.example.room_temperature.events.RoomAlarmEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

import static com.example.room_temperature.config.KafkaTopicConfig.ALARM_TOPIC;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendRoomAlarm(RoomAlarmEvent event) {

        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(
                ALARM_TOPIC,
                event.getRoomId().toString(),
                event
        );

        future.whenComplete((result, throwable) -> {
                if (throwable == null) {
                    log.info("KafkaProducer :: sendRoomAlarm :: Room alarm sent successfully : {}", event);
                } else {
                    log.error("KafkaProducer :: sendRoomAlarm :: Unable to send room alarm");
                }
            }
        );

    }
}
