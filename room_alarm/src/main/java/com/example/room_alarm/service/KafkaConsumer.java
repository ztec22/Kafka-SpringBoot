package com.example.room_alarm.service;


import com.example.room_alarm.events.RoomAlarmEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    public static final String ALARM_TOPIC = "alarm_topic";

    @RetryableTopic(attempts = "2")
    @KafkaListener(topics = ALARM_TOPIC, groupId = "defaultGroup")
    public void consumeRoomAlarmEvent(RoomAlarmEvent event, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic){

        log.info("KafkaConsumer :: consumeRoomAlarmEvent :: Consumed RoomAlarmEvent {} from topic: {}: ", event.toString(), topic);
        log.info("KafkaConsumer :: consumeRoomAlarmEvent :: Alarm triggered correctly for room with Id: {} ", event.getRoomId());

    }

    @DltHandler
    public void handleDltRoomAlarmEvent(RoomAlarmEvent event, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic){
        log.info("KafkaConsumer :: handleDltRoomAlarmEvent :: Sent to Dead Letter Topic : Couldn't process RoomAlarmEvent {} from topic: {}: ", event.toString(), topic);
    }
}
