package com.data.spi;

import com.data.utils.Store;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Produces;

import java.nio.file.ProviderNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@ApplicationScoped
public class ServiceLoader {

    @Inject
    ServiceLoader serviceLoader;

    @Inject
    Store store;

    ServiceInterface currentService;

    CompletableFuture<Void> runningService;
    String currentServiceName;
    //This is just for debugging and insight
    ArrayList<CompletableFuture<Void>> threadHist = new ArrayList<>();

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

    public void loadService(String serviceName) {
        ServiceInterface service = serviceLoader.provider(serviceName);
        service.setStore(store);
        service.setProperties();

        if (!threadHist.isEmpty()) {
            System.out.println("trying to switch");
            System.out.println(currentService.getClass());
            System.out.println("why no type oida");
            boolean isDone = currentService.stopService();
            //runningService.complete(null);

            System.out.println("is Stopped" + isDone);
            System.out.println("is done" + runningService.isDone());
            System.out.println("is canceled" + runningService.isCancelled());

            //currentService.completeExceptionally(new IllegalArgumentException("Jo methode fertig dua jetz switchen"));

        }

        runningService = service.invoke();
        threadHist.add(runningService);
        currentService = service;
        currentServiceName = serviceName;
    }

    public String getActiveService() {
        return currentServiceName;
    }
}
