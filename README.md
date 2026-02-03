# Kafka-SpringBoot

Proyecto de sistema de alarmas de incendios usando Kafka.
1. Compuesto por dos eventos:
- **RoomTempEvent**: temperatura de la habitación
```
{
    roomId: number 
    temperature: number 
}
```
- **RoomAlarmEvent**: alarma de la habitación
```
{
    roomId: number
}
```

2. Compuesto por dos topics de Kafka:

- **temperature_topic**: Almacena eventos RoomTempEvent
- **alarm_topic**: Almacena eventos RoomAlarmEvent

3. Esta compuesto por 3 microservicios:

- **room-monitor:** Produce eventos `RoomTempEvent`
- **room-temperature:** Consume eventos `RoomTempEvent` y produce eventos `RoomAlarmEvent` si la temperatura supera 35ºC
- **room-alarm**: Consume eventos `RoomAlarmEvent` y activa las alarmas de las habitaciones. 




