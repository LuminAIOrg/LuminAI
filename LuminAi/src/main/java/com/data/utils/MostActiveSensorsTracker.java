package com.data.utils;

import com.data.model.Sensor;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

@ApplicationScoped
public class MostActiveSensorsTracker {
    private final PriorityQueue<Sensor> sensorQueue;

    public MostActiveSensorsTracker() {
        this.sensorQueue = new PriorityQueue<Sensor>(new SensorComparator());
    }

    public synchronized void update(Sensor sensor, double value) {
        sensorQueue.remove(sensor); // Remove if already present to update the order
        sensorQueue.offer(sensor);
    }

    public synchronized Sensor getMostActiveSensor() {
        return sensorQueue.peek();
    }

    public synchronized List<Sensor> getSortedSensors() {
        return new ArrayList<>(sensorQueue);
    }
}
