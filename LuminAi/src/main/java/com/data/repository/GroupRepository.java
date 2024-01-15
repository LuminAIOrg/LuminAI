package com.data.repository;

import com.data.model.Group;
import com.data.model.Sensor;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class GroupRepository {

    @Inject
    EntityManager em;

    @Transactional
    public Group createOrGetGroup(String groupName) {
        Group group = em.createQuery("SELECT g FROM Group g WHERE g.name = :groupName", Group.class)
                .setParameter("groupName", groupName)
                .getSingleResult();
        if(group == null) {
            group = new Group(groupName);
            em.persist(group);
        }
        return group;
    }

    public List<Group> getAllGroups() {
        return em.createQuery("SELECT g FROM Group g", Group.class).getResultList();
    }

    @Transactional
    public void addGroup(Group group) {
        em.persist(group);
    }

    @Transactional
    public void addSensorToGroup(Sensor sensor, Group group) {
        group.addSensor(sensor);
        em.persist(sensor);
        em.persist(group);
    }

    public List<Sensor> getAllSensorsFromGroup(Group group) {
        return em.createQuery("SELECT s FROM Sensor s WHERE s.group = :group", Sensor.class)
                .setParameter("group", group)
                .getResultList();
    }
}
