package com.data.fetcher.driver;

import com.data.fetcher.DataFetcher;
import com.model.Data;
import com.repository.DataRepository;
import io.quarkus.scheduler.Scheduler;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.List;

public abstract class Driver implements DataFetcher {
    @Inject
    DataRepository dataRepository;

    @Inject
    Scheduler scheduler;
    @ConfigProperty(name = "apiUrl")
    public static String apiUrl;

    @Override
    public void invoke() {
        scheduler.newJob("runDriver")
                .setCron("0/5 * * * * ?")
                .setTask(executionContext -> {
                    try {
                        List<Data> data = runDriver();
                        dataRepository.addData(data);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .schedule();
    }

    public abstract List<Data> runDriver() throws Exception;
}
