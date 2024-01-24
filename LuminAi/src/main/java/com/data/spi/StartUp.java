package com.data.spi;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

@ApplicationScoped
public class StartUp {
    @Inject
    ServiceLoader serviceLoader;
    public void init(@Observes StartupEvent ev) {
        ServiceInterface serviceInterface = serviceLoader.provider();
        System.out.println(serviceInterface.getType().toString());
    }
}
