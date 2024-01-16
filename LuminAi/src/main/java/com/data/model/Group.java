package com.data.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "sensorGroup")
public class Group {
    @Id
    @GeneratedValue
    @Column(name = "g_id")
    private Long id;

    @Column(unique=true)
    private String name;

    @OneToMany
    @Nullable
    private ArrayList<Sensor> sensors;

    public Group(String name) {
        this.name = name;
    }

    public Group() {

    }

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

    public void addSensor(Sensor sensor){
        this.sensors.add(sensor);
        sensor.setGroup(this);
    }
}
