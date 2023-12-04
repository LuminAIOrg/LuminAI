package com.data.mqtt;

import com.data.model.Data;
import com.data.session.DataSocket;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class MqttConnection {

    @Inject
    DataSocket clients;

    @Incoming("mqtt-listener")
    public void consume(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Data dataObject = objectMapper.readValue(message, Data.class);
            clients.publish(dataObject);
        } catch (Exception e) {
            throw new RuntimeException("Error while parsing MQTT data", e);
        }


    }
}
