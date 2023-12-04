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

    public Data(String name, Double value, long timestamp) {
        setName(name);
        setValue(value);
        setTimestamp(timestamp);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return timestamp == data.timestamp && Objects.equals(name, data.name) && Objects.equals(value, data.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, timestamp, value);
    }
}
