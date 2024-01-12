package com.data.repository;

import com.data.model.Sensor;
import com.data.model.SensorData;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class SensorRepository {
    @Inject
    EntityManager entityManager;


    @Transactional
    public Sensor createOrGetSensor(String sensorName) {
        Sensor sensor = entityManager.createQuery("SELECT s FROM Sensor s WHERE s.name = :sensorName", Sensor.class)
                .setParameter("sensorName", sensorName)
                .getSingleResult();
        if(sensor == null) {
            sensor = new Sensor(sensorName);
            entityManager.persist(sensor);
        }
        return sensor;
    }

    @Transactional
    public void addData(List<SensorData> data) {
        if (data != null)  {
            data.forEach(d -> entityManager.persist(d));
        }
    }

    public List<Sensor> getAllSensors() {
        return entityManager.createQuery("SELECT s FROM Sensor s", Sensor.class).getResultList();
    }

    public List<SensorData> getAllDataFromSensor(Sensor sensor) {
        return entityManager.createQuery("SELECT d FROM SensorData d WHERE d.sensor = :sensor", SensorData.class)
                .setParameter("sensor", sensor)
                .getResultList();
    }
}
