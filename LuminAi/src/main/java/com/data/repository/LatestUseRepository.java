package com.data.repository;

import com.data.model.LatestUse;
import com.data.model.Sensor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;


import java.util.List;

@ApplicationScoped
public class LatestUseRepository {
    @Inject
    EntityManager entityManager;

    @Inject
    org.eclipse.microprofile.jwt.JsonWebToken jwt;

    public List<LatestUse> getAllLatestUseOfUser(){
        String jwtUserId = jwt.getClaim("sub");
        TypedQuery<LatestUse> query = this.entityManager.createQuery("SELECT lu from LatestUse lu where userId = :userId", LatestUse.class);
        query.setParameter("userId", jwtUserId);

        return query.getResultList();
    }

    @Transactional
    public LatestUse addLatestUse(long sensorId){
        Sensor sensor = entityManager.find(Sensor.class, sensorId);
        String jwtUserId = jwt.getClaim("sub");
        TypedQuery<LatestUse> query = this.entityManager.createQuery("SELECT lu from LatestUse lu where userId = :userId and sensor = :sensor", LatestUse.class);
        query.setParameter("userId", jwtUserId);
        query.setParameter("sensor", sensor);
        LatestUse foundLatestUse = query.getSingleResult();

        if (foundLatestUse != null){
            foundLatestUse.setLatestUse(System.currentTimeMillis());
        }
        else {
            LatestUse newLatestUse = new LatestUse();
            newLatestUse.setSensor(sensor);
            newLatestUse.setUserId(jwtUserId);
            newLatestUse.setLatestUse(System.currentTimeMillis());
            this.entityManager.persist(newLatestUse);
            return newLatestUse;
        }
        return foundLatestUse;
    }
}
