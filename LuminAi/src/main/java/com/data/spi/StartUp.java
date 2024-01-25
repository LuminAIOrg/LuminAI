package com.data.spi;

import com.data.model.SensorData;
import com.data.session.DataSocket;
import io.quarkus.runtime.StartupEvent;
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

    public void init(@Observes StartupEvent ev) {
        ServiceInterface service = serviceLoader.provider();

        HashMap<String, String> props = new HashMap<>();
        switch (service.getType()) {
            //TODO: The correct properties have to be set here
            case MQTT:
                props.put("foo", "bar");
                break;
            case DRIVER:
                props.put("foo", "bar");
                break;
        }
        service.setProps(props);

        List<SensorData> sensorData = service.fetchData();
        //TODO: Process and persist the data here

        sensorData.forEach(data->dataSocket.publish(data));
    }
}
