package com.data.fetcher.mqtt;

import com.data.model.SensorData;
import com.data.spi.FetcherType;
import com.data.spi.ServiceInterface;

import java.util.ArrayList;
import java.util.List;

public class MqttImpl implements ServiceInterface {

    @Override
    public List<SensorData> fetchData() {
        SensorData tempData = new SensorData();
        tempData.setValue(100.0);
        ArrayList<SensorData> sensorDataArrayList = new ArrayList<>();
        sensorDataArrayList.add(tempData);
        return sensorDataArrayList;
    }

    @Override
    public FetcherType getType() {
        return FetcherType.MQTT;
    }
}
