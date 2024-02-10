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
        List<Group> group = em.createQuery("SELECT s FROM Group s WHERE s.name = :groupName", Group.class)
                .setParameter("groupName", groupName)
                .getResultList();

        if(group.isEmpty()) {
            Group newGroup = new Group();
            newGroup.setName(groupName);
            em.merge(newGroup);
            em.flush();
            return newGroup;
        }
        return group.get(0);
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
