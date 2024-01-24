package com.data.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
public class SensorData {
    @Id
    @GeneratedValue
    @Column(name = "sd_id")
    private Long id;

    private Double value;

    @ManyToOne
    @Nullable
    @JoinColumn(name = "s_id")
    private Sensor sensor;

    public Long getId() {
        return id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Sensor getDevice() {
        return sensor;
    }

    public void setDevice(Sensor sensor) {
        this.sensor = sensor;
    }
}
