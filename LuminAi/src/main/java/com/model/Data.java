package com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

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
    private void setName(String name) {
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
}
