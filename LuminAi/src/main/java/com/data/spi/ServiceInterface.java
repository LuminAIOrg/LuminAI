package com.data.spi;

import com.data.model.SensorData;

import java.util.HashMap;
import java.util.List;

public interface ServiceInterface {
    void setProps(HashMap<String,String> props);
    List<SensorData> fetchData();
    FetcherType getType();
}
