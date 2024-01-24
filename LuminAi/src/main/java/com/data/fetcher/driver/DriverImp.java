package com.data.fetcher.driver;

import com.data.model.SensorData;
import com.data.spi.FetcherType;
import com.data.spi.ServiceInterface;

import java.util.List;

public class DriverImp implements ServiceInterface {
    @Override
    public List<SensorData> fetchData() {
        return null;
    }

    @Override
    public FetcherType getType() {
        return FetcherType.DRIVER;
    }
}
