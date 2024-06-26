package com.data.utils;

import com.data.model.Sensor;
import com.data.model.SensorData;

import java.util.Comparator;

public class SensorComparator implements Comparator<Sensor> {
    @Override
    public int compare(Sensor s1, Sensor s2) {
        double s1MaxValue = s1.getValues().stream().mapToDouble(SensorData::getValue).max().orElse(Double.MIN_VALUE);
        double s2MaxValue = s2.getValues().stream().mapToDouble(SensorData::getValue).max().orElse(Double.MIN_VALUE);
        return Double.compare(s2MaxValue, s1MaxValue); // Descending order
    }
}
