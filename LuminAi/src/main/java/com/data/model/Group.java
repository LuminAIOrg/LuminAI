package com.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "sensorGroup")
public class Group {
    @Id
    @GeneratedValue
    @Column(name = "g_id")
    private long id;

    private String name;

    @ManyToOne
    @Nullable
    @JoinColumn(name = "id", referencedColumnName = "g_id")
    @JsonIgnoreProperties({"groups"})
    private Group group;

    @OneToMany(mappedBy = "group")
    @Nullable
    @JsonIgnoreProperties({"group"})
    private ArrayList<Group> groups;

    @OneToMany(mappedBy = "group")
    @Nullable
    @JsonIgnoreProperties({"group"})
    private ArrayList<Sensor> sensors;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Nullable
    public ArrayList<Sensor> getSensors() {
        return sensors;
    }

    public long getId() {
        return id;
    }

    public void addSensor(Sensor sensor){
        this.sensors.add(sensor);
        sensor.setGroup(this);
    }
}
