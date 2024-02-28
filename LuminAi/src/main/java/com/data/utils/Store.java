package com.data.utils;

import com.data.model.Sensor;
import com.data.model.SensorData;
import com.data.repository.GroupRepository;
import com.data.repository.SensorDataRepository;
import com.data.repository.SensorRepository;
import com.data.websocket.DataSocket;
import io.quarkus.logging.Log;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.context.ManagedExecutor;

import java.util.concurrent.CompletableFuture;

@ApplicationScoped
public class Store {

    @Inject
    SensorDataRepository sensorDataRepository;

    @Inject
    SensorRepository sensorRepository;

    @Inject
    GroupRepository groupRepository;

    @Inject
    ManagedExecutor managedExecutor;

    BehaviorSubject<SensorData> subject;
    Consumer<SensorData> recipient;

    @Inject
    public Store() {
        this.subject = BehaviorSubject.createDefault(new SensorData());
    }

    @Inject
    DataSocket dataSocket;

    public BehaviorSubject<SensorData> getSubject() {
        return subject;
    }

    public void next() {
        SensorData sensorData = subject.getValue();
        if (sensorData == null) {
            Log.warn("Sensor Data SensorData is null");
        } else {
            checkAndPersistData(sensorData);
            dataSocket.publish(sensorData);
        }
        //try {
        //    TODO: get Recipient
        //    recipient.accept(sensorData);
        //}catch (Throwable e) {
        //    throw new RuntimeException("can't pe accepted by recipient \n" + e.getMessage());
        //}
    }

    private void checkAndPersistData(SensorData sensorData) {
        System.out.println(sensorData.getValue());
        Sensor mergedSensor = this.sensorRepository.createOrGetSensor(sensorData.getSensor().getName());
        sensorData.setSensor(mergedSensor);
        //TODO: Add group support
        //Group mergedGroup = this.groupRepository.createOrGetGroup(sensorData.getSensor().getGroup().getName());
        //sensorData.getSensor().setGroup(mergedGroup);
        this.sensorDataRepository.addData(sensorData);
    }
}
