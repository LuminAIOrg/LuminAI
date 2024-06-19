package com.data.repository;

import com.data.dto.LatestUseDto;
import com.data.model.LatestUse;
import com.data.model.Sensor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;


import java.util.List;

@ApplicationScoped
public class LatestUseRepository {
    @Inject
    EntityManager entityManager;

    @Inject
    org.eclipse.microprofile.jwt.JsonWebToken jwt;

    public List<LatestUseDto> getAllLatestUseOfUser(){
        String jwtUserId = jwt.getClaim("sub");
        TypedQuery<LatestUse> query = this.entityManager.createQuery("SELECT lu from LatestUse lu where userId = :userId order by lu.latestUse desc", LatestUse.class);
        query.setParameter("userId", jwtUserId);

        return query.getResultList().stream().map(LatestUseDto::toDto).toList();
    }

    @Transactional
    public LatestUseDto addLatestUse(long sensorId) {
        Sensor sensor = entityManager.find(Sensor.class, sensorId);
        String jwtUserId = jwt.getClaim("sub");
        TypedQuery<LatestUse> query = this.entityManager.createQuery("SELECT lu from LatestUse lu where userId = :userId and sensor = :sensor", LatestUse.class);
        query.setParameter("userId", jwtUserId);
        query.setParameter("sensor", sensor);

        try {
            LatestUse foundLatestUse = query.getSingleResult();
            foundLatestUse.setLatestUse(System.currentTimeMillis());
            return LatestUseDto.toDto(foundLatestUse);
        } catch (NoResultException e) {
            LatestUse newLatestUse = new LatestUse();
            newLatestUse.setSensor(sensor);
            newLatestUse.setUserId(jwtUserId);
            newLatestUse.setLatestUse(System.currentTimeMillis());
            this.entityManager.persist(newLatestUse);
            return LatestUseDto.toDto(newLatestUse);
        }
    }

}
