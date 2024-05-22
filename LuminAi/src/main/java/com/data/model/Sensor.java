package com.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Sensor {
    @Id
    @GeneratedValue
    @Column(name = "s_id")
    private Long id;

    private String name;
    private String unit;
    @ManyToOne
    @Nullable
    @JoinColumn(name = "g_id")
    @JsonIgnoreProperties({"sensors"})
    private Group group;
    @OneToMany(mappedBy = "sensorDataId.sensor")
    @JsonIgnoreProperties({"sensorDataId.sensor"})
    private List<SensorData> values = new ArrayList<>();

    @OneToMany(mappedBy = "sensor")
    @JsonIgnoreProperties({"sensor"})
    private List<LatestUse> latestUse;

    public Sensor(String name) {
        this.name = name;
    }

    public Sensor() {
    }

    public void addValue(final SensorData value) {
        this.values.add(value);
        value.setSensor(this);
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Nullable
    public Group getGroup() {
        return group;
    }

    public void setGroup(@Nullable Group group) {
        this.group = group;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SensorData> getValues() {
        return values;
    }
}
