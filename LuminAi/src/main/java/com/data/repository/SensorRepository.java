package com.data.repository;

import com.data.dto.DataDto;
import com.data.dto.SensorWithoutDataDto;
import com.data.model.Sensor;
import com.data.utils.MostActiveSensorsTracker;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class SensorRepository {

    @Inject
    EntityManager entityManager;

    @Inject
    MostActiveSensorsTracker mostActiveSensorsTracker;

    public List<SensorWithoutDataDto> getAllSensors() {
        return this.entityManager
                .createQuery("select new com.data.dto.SensorWithoutDataDto(s.id, s.name, s.unit) from Sensor s", SensorWithoutDataDto.class)
                .getResultList();
    }

    public List<DataDto> getAllDataFromSensor(long sensorId) {
        return entityManager
                .createQuery("SELECT new com.data.dto.DataDto(d.sensorDataId.timestamp, d.value) FROM SensorData d WHERE d.sensor.id = :sensorId", DataDto.class)
                .setParameter("sensorId", sensorId)
                .getResultList();
    }

    @Transactional
    public Sensor createOrGetSensor(String sensorName) {
        List<Sensor> sensor = entityManager.createQuery("SELECT s FROM Sensor s WHERE s.name = :sensorName", Sensor.class)
                .setParameter("sensorName", sensorName)
                .getResultList();

        if (sensor.isEmpty()) {
            Sensor newSensor = new Sensor();
            newSensor.setName(sensorName);
            entityManager.merge(newSensor);
            entityManager.flush();
            return newSensor;
        }
        return sensor.get(0);
    }

    public List<Sensor> getManusFuckingMostHappeningInEndpointSoHeDoesntCry(){
        List<Sensor> mostHappening = mostActiveSensorsTracker.getSortedSensors();
        System.out.println(mostHappening.get(0).getName());
        return mostHappening;
    }
}
