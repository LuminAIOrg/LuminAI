package com.data.spi;


import com.data.model.Driver;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Entity
public class ServiceInstance {
    @Id
    @GeneratedValue
    private int id;
    private String name = Integer.toString(id);

    @ManyToOne
    @JoinColumn(name = "serviceid")
    private Driver serviceId;
    private boolean disabled;

    @Transient
    @JsonIgnore
    private ServiceInterface service;

    @Transient
    @JsonIgnore
    private CompletableFuture<Void> thread;

    public ServiceInstanceDto toDto() {
        return new ServiceInstanceDto(this.id, this.name, this.serviceId.getName(), this.disabled);
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ServiceInterface getService() {
        return service;
    }

    public void setService(ServiceInterface service) {
        this.service = service;
    }

    public CompletableFuture<Void> getThread() {
        return thread;
    }

    public void setThread(CompletableFuture<Void> thread) {
        this.thread = thread;
    }

    public Driver getServiceId() {
        return serviceId;
    }

    public void setServiceId(Driver serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceInstance that = (ServiceInstance) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public String getServiceName() {
        return this.serviceId.getName();
    }
}
