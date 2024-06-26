package com.data.websocket;

import com.data.model.Sensor;
import com.data.utils.MostActiveSensorsTracker;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ServerEndpoint("/sensor-updates")
@ApplicationScoped
public class SensorWebSocket {

    @Inject
    MostActiveSensorsTracker mostActiveSensorsTracker;

    @Inject
    ObjectMapper objectMapper;

    private Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        // Handle incoming messages from clients if needed
    }

    public void broadcastSensorUpdate() {
        System.out.println("getting the data");
        List<Sensor> sortedSensors = mostActiveSensorsTracker.getSortedSensors();
        try {
            System.out.println("mapping it");
            String updateMessage = objectMapper.writeValueAsString(sortedSensors);
            for (Session session : sessions) {
                if (session.isOpen()) {
                    System.out.println("trying send but probably doesn't work because no session");
                    session.getAsyncRemote().sendText(updateMessage);
                }
            }
            System.out.println("finished with no problems :)");
        } catch (IOException e) {
            Log.warn("Parsing the data failed: " + e.getMessage());
        }
    }
}
