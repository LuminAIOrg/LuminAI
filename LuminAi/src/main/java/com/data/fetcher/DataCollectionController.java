package com.data.fetcher;

import com.data.fetcher.driver.EnergyDataFetcher;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

@ApplicationScoped
public class DataCollectionController {

    //TODO - make this dynamic with application.properties
    private final DataFetcher dataFetcher = new EnergyDataFetcher();

    void startDataCollection(@Observes StartupEvent ev) {
        dataFetcher.invoke();
    }
}
