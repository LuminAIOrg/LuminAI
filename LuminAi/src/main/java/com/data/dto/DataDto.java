package com.data.dto;

import com.data.model.SensorData;

public record DataDto (long timestamp, double value){
    public static DataDto toDto(SensorData data) {
        return new DataDto(data.getSensorDataId().getTimestamp(), data.getValue());
    }
}
