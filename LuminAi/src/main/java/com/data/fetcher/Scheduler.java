package com.data.fetcher;

import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class Scheduler {
    private static final List<DataFetcher> invokableClasses = new ArrayList<>();

    public static void addInvokeableClass(DataFetcher invokeable) {
        invokableClasses.add(invokeable);
    }

    @Scheduled(cron = "*/10 * * * * ?") // Executes every 10 seconds
    void yourScheduledTask() {
        invokableClasses.forEach(DataFetcher::invoke);
    }
}
