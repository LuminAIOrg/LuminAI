package com.data.model;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "data-collection-method")
public class DataCollectionMethod {
    @Id
    @GeneratedValue
    @Column(name = "dcm_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "data-collection-method")
    private ArrayList<CollectionProperties> collectionProperties;

    public DataCollectionMethod() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<CollectionProperties> getCollectionProperties() {
        return collectionProperties;
    }

    public void setCollectionProperties(ArrayList<CollectionProperties> collectionProperties) {
        this.collectionProperties = collectionProperties;
    }
}
