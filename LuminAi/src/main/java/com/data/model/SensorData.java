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

    private long timestamp;

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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
