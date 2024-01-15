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
        //PLEASE CLEAN THIS UP NEXT SPRINT
        //hawara wens ihr amoi eichare tasks on time mochn würdets dann miasat i ned imma im a öfi aufd nacht nu eichan bullshit fixn
        try {
            return entityManager.createQuery("SELECT s FROM Sensor s WHERE s.name = :sensorName", Sensor.class)
                    .setParameter("sensorName", sensorName)
                    .getSingleResult();
        } catch (Exception e) {
            Sensor newSensor = new Sensor(sensorName);
            entityManager.persist(newSensor);
            return newSensor;
        }
    }

    @Transactional
    public void addData(List<SensorData> data) {
        if (data != null) {
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
