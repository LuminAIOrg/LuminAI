package com.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
public class SensorData {
    @Id
    @GeneratedValue
    private Long v_id;

    private Double value;

    @ManyToOne
    @Nullable
    @JoinColumn(name = "d_id")
    private Sensor sensor;

    //<editor-fold desc="Getter and Setter">
    public Long getV_id() {
        return v_id;
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
    //</editor-fold>
}
