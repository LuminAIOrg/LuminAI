package com.data.fetcher.driver;

import com.data.fetcher.DataFetcher;
import com.model.Data;
import com.repository.DataRepository;
import io.quarkus.scheduler.Scheduler;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.*;

public abstract class Driver implements DataFetcher {
    @Inject
    DataRepository dataRepository;

    @Inject
    Scheduler scheduler;
    @ConfigProperty(name = "apiUrl")
    public static String apiUrl;

    @Override
    public void invoke() {
        //TODO - add cronjob so that this is executed every x seconds
        //TODO - add result of runDriver() to repository
    }

    public abstract List<Data> runDriver() throws Exception;
}
