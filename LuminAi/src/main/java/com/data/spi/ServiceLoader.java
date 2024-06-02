package com.data.spi;

import com.data.utils.Store;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Produces;

import java.nio.file.ProviderNotFoundException;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class ServiceLoader {

    @Inject
    Store store;

    @Inject
    ServiceInstanceRepository serviceInstanceManager;

    @Produces
    public ServiceInterface provider(String serviceName) {
        java.util.ServiceLoader<ServiceInterface> loader = java.util.ServiceLoader.load(ServiceInterface.class);
        for (ServiceInterface provider : loader) {
            if (serviceName.equals(provider.getClass().getName())) {
                return provider;
            }
        }
        throw new ProviderNotFoundException("Data provider " + serviceName + " not found");
    }

    public List<String> getAllServices() {
        java.util.ServiceLoader<ServiceInterface> loader = java.util.ServiceLoader.load(ServiceInterface.class);
        return loader.stream().map(curr -> curr.get().getClass().getName()).toList();
    }

    public void startService(String serviceName) {
        ServiceInterface service = provider(serviceName);
        service.setStore(store);
        service.setProperties();
        serviceInstanceManager.createServiceInstance(service, service.invoke());
    }

    public Set<ServiceInstance> getAllServiceInstances() {
        return serviceInstanceManager.getServices();
    }

    public boolean revokeInstace(int instanceId) {
        return serviceInstanceManager.stopAndDeleteServiceInstance(instanceId);
    }

    public ServiceInstance disableInstance(int instanceId) {
        if (serviceInstanceManager.disableInstance(instanceId)) {
            return serviceInstanceManager.getService(instanceId);
        }
        throw new RuntimeException("Error while disabling Instance");
    }

    @Transactional
    public ServiceInstance enableInstance(int instanceId) {
        serviceInstanceManager.getService(instanceId).setDisabled(false);
        reinvokeOrphanInstances();
        return serviceInstanceManager.getService(instanceId);
    }

    public void reinvokeOrphanInstances() {
        serviceInstanceManager.getServices().stream()
                .filter(it -> (it.getService() == null || it.getThread() == null) && !it.isDisabled())
                .forEach(it -> {
                    ServiceInterface service = provider(it.getServiceName());
                    service.setStore(store);
                    service.setProperties();
                    it.setService(service);
                    it.setThread(service.invoke());
                    serviceInstanceManager.mergeServiceInstamce(it);

                });
    }
}
