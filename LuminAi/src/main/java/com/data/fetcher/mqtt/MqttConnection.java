package com.data.fetcher.mqtt;

import com.data.fetcher.DataFetcher;
import com.data.repository.SensorDataRepository;
import com.data.session.DataSocket;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class MqttConnection implements DataFetcher {
    @Inject
    DataSocket clients;

    @Inject
    SensorDataRepository repository;

    @ConfigProperty(name = "mp.messaging.incoming.mqtt-listener.topic")
    String topicName;

    @Transactional
    @Incoming("mqtt-listener")
    public void consume(String message) {
        //ObjectMapper objectMapper = new ObjectMapper();
        //try {
        //    Data dataObject = objectMapper.readValue(message, Data.class);
        //    String[] split = topicName.split("/");
        //    dataObject.setName(split[split.length - 2]);
        //    dataObject.setUnit("°");
        //    repository.addData(dataObject);
        //    clients.publish(dataObject);
//
        //} catch (JsonProcessingException e) {
        //    Log.error("error parsing into Data Object: " + message);
        //}
    }

    @Override
    public void invoke() {
        System.out.println("Invoked MQTT Connection");
        //Todo Only Start when this method is invoked
    }
}
