package com.data.mqtt;

import com.session.UpdateSocket;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class MqttConnection {

    @Inject
    UpdateSocket clients;

    @Incoming("mqtt-listener")
    public void consume(String message) {
        clients.sendUpdates(message);
    }
}
