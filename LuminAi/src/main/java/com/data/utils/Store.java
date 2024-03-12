package com.data.utils;

import com.data.model.Sensor;
import com.data.model.SensorData;
import com.data.model.SensorDataId;
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

import java.util.ArrayList;
import java.util.List;
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

    BehaviorSubject<List<SensorData>> subjectList;

    @Inject
    public Store() {
        this.subject = BehaviorSubject.createDefault(new SensorData());
        this.subjectList = BehaviorSubject.createDefault(new ArrayList<SensorData>());
    }

    @Inject
    DataSocket dataSocket;

    public BehaviorSubject<SensorData> getSubject() {
        return subject;
    }

    public BehaviorSubject<List<SensorData>> getSubjectList() {
        return subjectList;
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

    public void addAll() {
        if(subjectList.getValue() == null){
            Log.info("Data List is null!");
            return;
        }
        for(SensorData sensorData : subjectList.getValue()){
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
    }

    private void checkAndPersistData(SensorData sensorData) {
        Sensor mergedSensor = this.sensorRepository.createOrGetSensor(sensorData.getSensor().getName());
        sensorData.setSensor(mergedSensor);
        //TODO: Add group support
        //Group mergedGroup = this.groupRepository.createOrGetGroup(sensorData.getSensor().getGroup().getName());
        //sensorData.getSensor().setGroup(mergedGroup);
        //System.out.println("gibts eh scho oda soÖLSDKJFSLÖDFJDFÖLS: " + mergedSensor.getValues().stream().anyMatch(sd -> sd.getSensorDataId().getTimestamp() == sensorData.getSensorDataId().getTimestamp()));
        this.sensorDataRepository.addData(sensorData);
        /*
        if (!mergedSensor.getValues().stream()
                .anyMatch(sd -> sd.getSensorDataId().getTimestamp() == sensorData.getSensorDataId().getTimestamp())) {
            System.out.println("SensorData added! TODO: check timestamp only.");

        } else {
            System.out.println("SensorData already exists! TODO: check timestamp only.");
        }
        */
    }
}
