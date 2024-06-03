package com.data.model;

import jakarta.persistence.*;

@Entity
@Table(name = "latest_use")
public class LatestUse {
    @Id
    @GeneratedValue
    @Column(name = "lu_id")
    private Long id;

    private String userId;

    @ManyToOne
    @JoinColumn(name = "s_id", nullable = false)
    private Sensor sensor;

    private Long latestUse;


    public Sensor getSensor(){
        return this.sensor;
    }

    public void setSensor(Sensor sensor){
        this.sensor = sensor;
    }
    public Long getId() {
        return id;
    }

    public Long getLatestUse() {
        return latestUse;
    }

    public void setLatestUse(Long latestUse) {
        this.latestUse = latestUse;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
