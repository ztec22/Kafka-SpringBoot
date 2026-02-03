package com.example.room_temperature.events;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RoomAlarmEvent {
    Integer roomId;
}
