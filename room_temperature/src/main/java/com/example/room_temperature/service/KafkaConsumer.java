package com.example.room_temperature.service;


import com.example.room_temperature.events.RoomAlarmEvent;
import com.example.room_temperature.events.RoomTempEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import static com.example.room_temperature.config.KafkaTopicConfig.TEMPERATURE_TOPIC;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    private final KafkaProducer kafkaProducer;

    @RetryableTopic(attempts = "2")
    @KafkaListener(topics = TEMPERATURE_TOPIC, groupId = "defaultGroup")
    public void consumeRoomTempEvent(RoomTempEvent event, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic){

        log.info("KafkaConsumer :: consumeRoomTempEvent :: Consumed RoomTempEvent {} from topic: {}: ", event.toString(), topic);

        if(event.getTemperature() > 35) {

            RoomAlarmEvent alarmEvent = RoomAlarmEvent.builder()
                    .roomId(event.getRoomId())
                    .build();

            kafkaProducer.sendRoomAlarm(alarmEvent);
        }
    }

    @DltHandler
    public void handleDltRoomTempEvent(RoomTempEvent event, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic){
        log.info("KafkaConsumer :: handleDltRoomTempEvent :: Sent to Dead Letter Topic : Couldn't process RoomTempEvent {} from topic: {}: ", event.toString(), topic);
    }
}
