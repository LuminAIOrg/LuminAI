package com.data.spi;

import com.data.model.SensorData;

import java.util.List;

public interface ServiceInterface {
    List<SensorData> fetchData();
    FetcherType getType();
}
