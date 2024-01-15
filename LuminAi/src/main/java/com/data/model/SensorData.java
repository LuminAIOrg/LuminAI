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

    private long timeStamp;

    @ManyToOne
    @Nullable
    @JoinColumn(name = "d_id")
    private Sensor sensor;

    //<editor-fold desc="Getter and Setter">
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

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    //</editor-fold>
}
