package com.data.spi;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@ApplicationScoped
public class ServiceInstanceRepository {

    @Inject
    EntityManager entityManager;

    Set<ServiceInstance> serviceInstances = new HashSet<>();

    @Transactional
    public void createServiceInstance(ServiceInterface service, CompletableFuture<Void> instance) {
        ServiceInstance serviceInstance = new ServiceInstance();
        serviceInstance.setService(service);
        serviceInstance.setThread(instance);
        serviceInstance.setServiceName(service.getClass().getName());
        serviceInstance.setDisabled(false);
        entityManager.persist(serviceInstance);
        serviceInstance.setName(Integer.toString(serviceInstance.getId()));
        serviceInstances.add(serviceInstance);
    }

    public Set<ServiceInstance> getServices() {
        List<ServiceInstance> dbSerivices = entityManager.createQuery("select s from ServiceInstance s", ServiceInstance.class)
                .getResultList();
        for (ServiceInstance serviceInstance : serviceInstances) {
            dbSerivices.stream()
                    .filter(it -> it.getId() == serviceInstance.getId())
                    .findFirst()
                    .ifPresent(it -> {
                        it.setThread(serviceInstance.getThread());
                        it.setService(serviceInstance.getService());
                    });
        }
        return new HashSet<>(dbSerivices);
    }

    public ServiceInstance getService(int serviceId) {
        ServiceInstance serviceInstance = getServices().stream().filter(it -> it.getId() == serviceId).findFirst().orElseGet(null);
        if (serviceInstance == null) throw new IllegalArgumentException("serviceInstance not found");
        return serviceInstance;
    }

    @Transactional
    public boolean stopAndDeleteServiceInstance(int serviceId) {
        ServiceInstance serviceInstance = getService(serviceId);
        boolean successfullyStoped = true;
        if (serviceInstance.getService() != null && serviceInstance.getThread() != null) {
            successfullyStoped = serviceInstance.getService().stopService();
        }
        entityManager.remove(serviceInstance);
        serviceInstances.remove(serviceInstance);
        return successfullyStoped;
    }

    public void mergeServiceInstamce(ServiceInstance serviceInstance) {
        serviceInstances.add(serviceInstance);
    }

    @Transactional
    public boolean disableInstance(int instanceId) {
        ServiceInstance serviceInstance = getService(instanceId);
        serviceInstance.setDisabled(true);
        serviceInstances.remove(serviceInstance);
        if (serviceInstance.getThread() != null && serviceInstance.getService() != null) {
            boolean successfullyStoped = serviceInstance.getService().stopService();
            serviceInstance.setService(null);
            serviceInstance.setThread(null);
            return successfullyStoped;
        }
        return true;
    }
}
