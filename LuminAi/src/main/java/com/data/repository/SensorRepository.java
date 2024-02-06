package com.data.repository;

import com.data.dto.SensorWithoutDataDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class SensorRepository {

    @Inject
    EntityManager entityManager;

    public List<SensorWithoutDataDto> getAllSensors() {
        return this.entityManager
                .createQuery("select new com.data.dto.SensorWithoutDataDto(s.id, s.name, s.unit) from Sensor s", SensorWithoutDataDto.class)
                .getResultList();
    }
}
