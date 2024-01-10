package com.data.repository;

import com.data.model.Sensor;
import com.data.model.SensorData;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class SensorDataRepository {
    @Inject
    EntityManager entityManager;

    @Transactional
    public void addData(SensorData data) {
        entityManager.persist(data);
    }

    @Transactional
    public void addData(List<SensorData> data) {
        if (data != null)  {
            data.forEach(d -> entityManager.persist(d));
        }
    }


    public List<SensorData> getAllData() {
        return entityManager.createQuery("SELECT d FROM SensorData d", SensorData.class).getResultList();
    }

    public List<SensorData> getAllDataFromSensor(Sensor sensor) {
        return entityManager.createQuery("SELECT d FROM SensorData d WHERE d.sensor = :sensor", SensorData.class)
                .setParameter("sensor", sensor)
                .getResultList();
    }
}
