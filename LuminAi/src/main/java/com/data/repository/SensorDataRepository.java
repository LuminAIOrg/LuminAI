package com.data.repository;

import com.data.dto.DataDto;
import com.data.dto.PageDto;
import com.data.dto.SensorDto;
import com.data.dto.SensorWithoutDataDto;
import com.data.model.SensorData;
import com.data.model.SensorDataId;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.exception.ConstraintViolationException;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class SensorDataRepository {
    @Inject
    EntityManager entityManager;

    @Transactional
    public void addData(SensorData data) {
        try {
            entityManager.persist(data);
        } catch (Exception e) {
            Log.warn("Duplicate Data or other Error: " + e);
        }
    }

    @Transactional
    public void addData(List<SensorData> data) {
        if (data != null) {
            data.forEach(d -> entityManager.persist(d));
        }
    }


    public PageDto getPage(int pageId, int pageSize) {
        List<SensorWithoutDataDto> sensorIds = entityManager.createQuery("select new com.data.dto.SensorWithoutDataDto(s.id, s.name, s.unit) from Sensor s", SensorWithoutDataDto.class)
                .getResultList();
        ArrayList<SensorDto> sensors = new ArrayList<>();
        for (SensorWithoutDataDto sensor : sensorIds) {
            List<DataDto> data = entityManager.createQuery("select new com.data.dto.DataDto(d.sensorDataId.timestamp, d.value) from SensorData d where d.sensorDataId.sensor.id = :id order by sensorDataId.timestamp desc", DataDto.class)
                    .setFirstResult(pageId * pageSize)
                    .setMaxResults(pageSize)
                    .setParameter("id", sensor.id())
                    .getResultList();
            sensors.add(new SensorDto(sensor.id(), sensor.name(), sensor.unit(), data));
        }
        return new PageDto(pageId, sensors);
    }
}
