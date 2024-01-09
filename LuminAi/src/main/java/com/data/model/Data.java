package com.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

import java.util.Objects;

@NamedQuery(name = Data.FIND_ALL, query = "SELECT d FROM Data d"    )

@Entity
public class Data {

    public static final String FIND_ALL = "Data.findAll";

    @Id
    private String name;
    @Id
    private long timestamp;
    private Double value;

    private String unit;

    public Data(String name, long timestamp, Double value, String unit) {
        this.name = name;
        this.timestamp = timestamp;
        this.value = value;
        this.unit = unit;
    }

    public Data() {}

    public String getName() {
        return name;
    }
    public Double getValue() {
        return value;
    }
    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Illegal data name:" + name);
        }
        this.name = name;
    }
    private void setValue(Double value) {
        this.value = value;
    }

    public long getTimestamp() {
        return timestamp;
    }

    private void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return timestamp == data.timestamp && Objects.equals(name, data.name) && Objects.equals(value, data.value) && Objects.equals(unit, data.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, timestamp, value, unit);
    }
}
