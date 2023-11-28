package com.mqtt;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class MqttConnection {
    @Incoming("mqtt-listener")
    public void consume(String message) {
        Log.info("Message received: " + message);
    }
}
