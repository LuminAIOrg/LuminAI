package com.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.quarkus.runtime.annotations.IgnoreProperty;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;


@Entity
public class SensorData {
    @Id
    SensorDataId sensorDataId;

    private Double value;

    @ManyToOne
    @Nullable
    @JoinColumn(name = "s_id")
    private Sensor sensor;

    public SensorDataId getSensorDataId() {
        return sensorDataId;
    }

    public void setSensorDataId(SensorDataId sensorDataId) {
        this.sensorDataId = sensorDataId;
    }

    @Nullable
    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(@Nullable Sensor sensor) {
        this.sensor = sensor;
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
