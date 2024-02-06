package com.data.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SensorDataId implements Serializable {
    @GeneratedValue
    @Column(name = "sd_id")
    private Long id;
    private long timestamp;

    public Long getId() {
        return id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorDataId that = (SensorDataId) o;
        return timestamp == that.timestamp && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timestamp);
    }
}
