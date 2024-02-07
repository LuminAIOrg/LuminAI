package com.data.fetcher.driver;

import com.data.model.SensorData;
import com.data.spi.FetcherType;
import com.data.spi.ServiceInterface;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class DriverImpl implements ServiceInterface {

    @Override
    public void setSubject(BehaviorSubject<SensorData> subject) {

    }

    @Override
    public void setProperties() {

    }

    @Override
    public CompletableFuture<Void> invoke() {
        System.out.println("Driver got Invoked");
        return null;
    }
    @Override
    public FetcherType getType() {
        return FetcherType.DRIVER;
    }
}
