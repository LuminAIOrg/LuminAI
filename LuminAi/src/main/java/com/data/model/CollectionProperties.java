package com.data.model;

import jakarta.persistence.*;

@Entity
public class CollectionProperties {

    @Id
    @GeneratedValue
    @Column(name = "cp_id")
    private Long id;

    private String propertyKey;

    private String propertyValue;

    @ManyToOne
    @JoinColumn(name = "dcm_id")
    private DataCollectionMethod dataCollectionMethod;

    public CollectionProperties() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getPropertyKey() {
        return propertyKey;
    }

    public void setPropertyKey(String propertyKey) {
        this.propertyKey = propertyKey;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    public DataCollectionMethod getDataCollectionMethod() {
        return dataCollectionMethod;
    }

    public void setDataCollectionMethod(DataCollectionMethod dataCollectionMethod) {
        this.dataCollectionMethod = dataCollectionMethod;
    }
}
