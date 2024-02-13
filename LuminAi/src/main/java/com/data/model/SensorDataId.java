package com.data.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SensorDataId implements Serializable {
    @ManyToOne
    @Nullable
    @JoinColumn(name = "s_id")
    private Sensor sensor;

    private long timestamp;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Nullable
    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(@Nullable Sensor sensor) {
        this.sensor = sensor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorDataId that = (SensorDataId) o;
        return timestamp == that.timestamp && Objects.equals(sensor, that.sensor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sensor, timestamp);
    }
}
