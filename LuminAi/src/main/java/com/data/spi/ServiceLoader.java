package com.data.spi;

import com.data.utils.Store;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.Produces;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.nio.file.ProviderNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@ApplicationScoped
public class ServiceLoader {

    @Inject
    ServiceLoader serviceLoader;

    @Inject
    Store store;

    @Inject
    EntityManager entityManager;

    CompletableFuture<Void> currentService;

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

    //This is just for debugging and insight
    ArrayList<CompletableFuture<Void>> threadHist = new ArrayList<>();

    public void loadService(String serviceName) {
        ServiceInterface service = serviceLoader.provider(serviceName);
        service.setStore(store);
        service.setProperties();
        CompletableFuture<Void> newService = service.invoke();
        if (currentService != null) {
            currentService.cancel(true);
        }
        threadHist.add(newService);
        currentService = newService;
    }
}
