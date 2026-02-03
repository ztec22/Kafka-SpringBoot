package com.example.room_temperature.events;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RoomTempEvent {
    Integer roomId;
    Integer temperature;
}
