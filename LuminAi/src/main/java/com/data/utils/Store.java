package com.data.utils;

import com.data.model.Group;
import com.data.model.SensorData;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.io.IOException;
@ApplicationScoped
public class Store {

    BehaviorSubject<SensorData> subject;

    @Inject
    public Store() {

        this.subject = BehaviorSubject.createDefault(new SensorData());
    }
    Consumer<SensorData> recipient;
    public void next(){
        SensorData sensorData = subject.getValue();
        if (sensorData != null) {
            System.out.println(sensorData.getValue());
        }
        try {
            recipient.accept(sensorData);
        }catch (Throwable e) {
           throw new RuntimeException("can't pe accepted by recipient \n" + e.getMessage());
        }

    }
}
