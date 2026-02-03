package com.example.room_alarm.events;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RoomAlarmEvent {
    Integer roomId;
}
