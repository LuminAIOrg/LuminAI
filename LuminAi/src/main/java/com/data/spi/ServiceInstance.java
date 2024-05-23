package com.data.spi;


import jakarta.persistence.*;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Entity
public class ServiceInstance {
    @Id
    @GeneratedValue
    private int id;
    private String name = Integer.toString(id);
    private String serviceName;
    private boolean disabled;

    @Transient
    private ServiceInterface service;

    @Transient
    private CompletableFuture<Void> thread;

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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
}
