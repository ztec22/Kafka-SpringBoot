package com.example.room_monitor.controller;

import com.example.room_monitor.events.RoomTempEvent;
import com.example.room_monitor.service.KafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/monitor")
@RequiredArgsConstructor
public class RoomTempController {

    private final KafkaProducer kafkaProducer;

    @PostMapping("/temp")
    public ResponseEntity<String> postRoomTemp(@RequestBody RoomTempEvent roomTemp){
        kafkaProducer.sendRoomTemperature(roomTemp);
        return ResponseEntity.ok("success");
    }
}
