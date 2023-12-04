package com.data.repository;

import com.model.Data;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class DataRepository {
    @Inject
    EntityManager entityManager;

    @Transactional
    public void addData(Data data) {
        entityManager.persist(data);
    }

    @Transactional
    public void addData(List<Data> data) {
        if (data != null)  {
            data.forEach(d -> entityManager.persist(d));
        }
    }


    public List<Data> getAllData() {
        return entityManager.createNamedQuery(Data.FIND_ALL, Data.class).getResultList();
    }
}
