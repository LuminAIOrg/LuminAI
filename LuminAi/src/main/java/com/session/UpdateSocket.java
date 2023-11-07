package com.session;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;


@ServerEndpoint("/subscribeUpdates")
@ApplicationScoped
public class UpdateSocket {

    @Inject
    private Logger loger;

    Set<Session> sessions = new HashSet<>();

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        sessions.remove(session);
        loger.log(java.util.logging.Level.SEVERE, "Error with user websocket", throwable);
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }

    private void sendUpdates(String message) {
        sessions.forEach(session -> {
            session.getAsyncRemote().sendObject(message, result -> {
                if (result.getException() != null) {
                    loger.log(java.util.logging.Level.SEVERE, "Unable to send message: " + result.getException());
                }
            });
        });
    }
}
