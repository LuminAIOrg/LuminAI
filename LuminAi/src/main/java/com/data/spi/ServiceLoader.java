package com.data.spi;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Produces;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import java.nio.file.ProviderNotFoundException;

@ApplicationScoped
public class ServiceLoader {

    @ConfigProperty(name = "dataCollectionMethod")
    String providerName;

    @Produces
    public ServiceInterface provider() {
        java.util.ServiceLoader<ServiceInterface> loader = java.util.ServiceLoader.load(ServiceInterface.class);
        for (ServiceInterface provider : loader) {
            if (providerName.equals(provider.getClass().getName())) {
                return provider;
            }
        }
        throw new ProviderNotFoundException("Data provider " + providerName + " not found");
    }
}
