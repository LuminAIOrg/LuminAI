package com.data.fetcher;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class Scheduler {
    private static final List<DataFetcher> invokableClasses = new ArrayList<>();

    public static void addInvokeableClass(DataFetcher invokeable) {
        invokableClasses.add(invokeable);
    }

    @Scheduled(cron = "{interval.time.cron:disabled}")
    void yourScheduledTask() {
        invokableClasses.forEach(DataFetcher::invoke);
    }
}
