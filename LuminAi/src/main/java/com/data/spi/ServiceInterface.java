package com.data.spi;

import com.data.model.SensorData;
import com.data.utils.Store;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.Subject;

import java.util.HashMap;
import java.util.List;

public interface ServiceInterface {
    FetcherType getType();

    void setSubject(BehaviorSubject<SensorData> subject);
}
