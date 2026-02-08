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

## Entorno local

1. Iniciar kafka, kafka-ui, elasticsearch, logstash, kibana
```bash
docker compose up
```
2. Ejecutar los proyectos room-monitor, room-temperature y room-alarm desde el IDE Intellij

3. Acceder a kafka-ui desde: http://localhost:9000

4. Acceder al microservicio room-monitor: http://localhost:8080/swagger-ui/index.html

5. Acceder a Kibana: http://localhost:5601
   - Menú > Elastic Search > Index Managment > Indices
   - Click en uno de los indices y después el botón Discover Index


