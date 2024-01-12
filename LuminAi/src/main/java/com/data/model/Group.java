package com.data.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class Group {
    @Id
    @GeneratedValue
    @Column(name = "g_id")
    private long id;

    private String name;

    @OneToMany
    @Nullable
    private ArrayList<Sensor> sensors;

    //<editor-fold desc="Getter and Setter">
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
    //</editor-fold>

    public void addSensor(Sensor sensor){
        this.sensors.add(sensor);
        sensor.setGroup(this);
    }
}
