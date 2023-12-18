package com.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Sensor {
    @Id
    @GeneratedValue
    private Long d_id;

    private String name;

    @ManyToOne
    @Nullable
    private Group group;

    @OneToMany
    private List<SensorData> values = new ArrayList<>();

    public void addValue(final SensorData value){
        this.values.add(value);
        value.setDevice(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Nullable
    public Group getGroup() {
        return group;
    }

    public void setGroup(@Nullable Group group) {
        this.group = group;
    }
}
