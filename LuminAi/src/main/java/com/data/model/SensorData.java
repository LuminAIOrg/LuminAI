package com.data.model;

import jakarta.persistence.*;


@Entity
public class SensorData {
    @EmbeddedId
    SensorDataId sensorDataId;
    private Double value;

    public SensorDataId getSensorDataId() {
        return sensorDataId;
    }

    public void setSensorDataId(SensorDataId sensorDataId) {
        this.sensorDataId = sensorDataId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public void setSensor(Sensor sensor) {
        this.sensorDataId.setSensor(sensor);
    }

    public Sensor getSensor() {
        return this.sensorDataId.getSensor();
    }
}
