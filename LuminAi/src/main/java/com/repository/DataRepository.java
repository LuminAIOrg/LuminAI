package com.repository;

import com.model.Data;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class DataRepository {
    @Inject
    EntityManager entityManager;

    public void addData(Data data) {
        entityManager.persist(data);
    }

    public void addData(List<Data> data) {
        if (data != null)  {
            data.forEach(d -> entityManager.persist(d));
        }
    }


    public List<Data> getAllData() {
        return entityManager.createNamedQuery(Data.FIND_ALL, Data.class).getResultList();
    }
}
