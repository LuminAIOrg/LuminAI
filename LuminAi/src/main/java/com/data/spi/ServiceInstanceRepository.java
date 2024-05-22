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

    @Transactional
    public boolean stopSerives(int serviceId) {
        ServiceInstance serviceInstance = getServices().stream().filter(it -> it.getId() == serviceId).findFirst().orElseGet(null);
        if (serviceInstance == null) throw new IllegalArgumentException("serviceInstance not found");
        boolean successfullyStoped = serviceInstance.getService().stopService();
        entityManager.remove(serviceInstance);
        serviceInstances.remove(serviceInstance);
        return successfullyStoped;
    }

    public void removeAllOrphans() {
        getServices().stream().filter(it -> it.getService() == null || it.getThread() == null).forEach(it -> {
            entityManager.remove(it);
            serviceInstances.remove(it);
        });
    }

    public void mergeServiceInstamce(ServiceInstance serviceInstance) {
        serviceInstances.add(serviceInstance);
    }
}
