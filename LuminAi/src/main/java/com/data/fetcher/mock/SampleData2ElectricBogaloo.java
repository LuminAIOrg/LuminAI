package com.data.fetcher.mock;

import com.data.model.Sensor;
import com.data.model.SensorData;
import com.data.model.SensorDataId;
import com.data.spi.FetcherType;
import com.data.spi.ServiceInterface;
import com.data.utils.Store;
import io.quarkus.logging.Log;
import io.quarkus.runtime.annotations.RegisterForReflection;
import io.smallrye.common.annotation.Blocking;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.Instant;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
@Blocking
@RegisterForReflection
public class SampleData2ElectricBogaloo implements ServiceInterface {
    private Store store;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    @Override
    public FetcherType getType() {
        return FetcherType.MOCK;
    }

    @Override
    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public void setProperties() {
        Log.info("This Mocked Data doesnt need Properties");
    }

    public void runDriver() {
        Log.info("Mocked data supplied");

        SensorData sensorData = new SensorData();
        SensorDataId sensorDataId = new SensorDataId();
        Sensor sensor = new Sensor();
        sensor.setName("MockedSensor2");
        sensor.setUnit("MockedDegrees");

        sensorDataId.setTimestamp(Instant.now().toEpochMilli());
        sensorDataId.setSensor(sensor);
        sensorData.setSensorDataId(sensorDataId);

        double min = 50;
        double max = 300;
        Random r = new Random();
        double randomValue = min + (max - min) * r.nextDouble();
        sensorData.setValue(randomValue);

        store.getSubject().onNext(sensorData);
        store.next();
    }

    @Override
    public CompletableFuture<Void> invoke() {
        return CompletableFuture.runAsync(() -> {
            scheduler.scheduleAtFixedRate(this::runDriver, 0, 2, TimeUnit.SECONDS);
        });
    }
}
