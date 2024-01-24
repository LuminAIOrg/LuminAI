package com.data.fetcher.driver;

import com.data.model.SensorData;
import com.data.spi.FetcherType;
import com.data.spi.ServiceInterface;

import java.util.HashMap;
import java.util.List;

public class DriverImpl implements ServiceInterface {
    @Override
    public void setProps(HashMap<String, String> props) {
        //TODO
    }

    @Override
    public List<SensorData> fetchData() {
        return null;
    }

    @Override
    public FetcherType getType() {
        return FetcherType.DRIVER;
    }
}
