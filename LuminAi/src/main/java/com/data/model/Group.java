package com.data.model;

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

    @Column(unique = true)
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

    public long getId() {
        return id;
    }

    public void addSensor(Sensor sensor) {
        if (this.sensors == null) {
            this.sensors = new ArrayList<>();
        }
        this.sensors.add(sensor);
    }
}
