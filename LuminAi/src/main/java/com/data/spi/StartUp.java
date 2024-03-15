package com.data.spi;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.ConfigProvider;

@ApplicationScoped
public class StartUp {
    @Inject
    ServiceLoader serviceLoader;

    public void init(@Observes StartupEvent ev) {
        String config = ConfigProvider.getConfig().getValue("defaultDataCollectionMethod", String.class);
        serviceLoader.loadService(config);
    }
}
