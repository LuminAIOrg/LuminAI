package com.data.spi;

import com.data.fetcher.mqtt.MqttConnection;
import com.data.model.SensorData;
import com.data.session.DataSocket;
import com.data.utils.Store;
import io.quarkus.runtime.StartupEvent;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

import java.util.HashMap;
import java.util.List;

@ApplicationScoped
public class StartUp {
    @Inject
    ServiceLoader serviceLoader;

    @Inject
    DataSocket dataSocket;

    @Inject
    Store store;

    public void init(@Observes StartupEvent ev) {
        ServiceInterface service = serviceLoader.provider();
        service.setStore(store);
        service.setProperties();
        service.invoke();

        //List<SensorData> sensorData = service.fetchData();
        //TODO: Process and persist the data here

        //sensorData.forEach(data->dataSocket.publish(data));
    }
}
