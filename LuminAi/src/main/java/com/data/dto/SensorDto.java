package com.data.dto;

import com.data.model.Sensor;

import java.util.List;

public record SensorDto(Long id, String name, String unit, List<DataDto> data) {
    public static SensorDto toDto(Sensor sensor) {
        return new SensorDto(
                sensor.getId(),
                sensor.getName(),
                sensor.getUnit(),
                sensor.getValues()
                        .stream().map(DataDto::toDto).toList()
        );
    }


    public static SensorDto toDto(Sensor sensor, int limitDataLength) {
        return new SensorDto(
                sensor.getId(),
                sensor.getName(),
                sensor.getUnit(),
                sensor.getValues()
                        .stream().limit(limitDataLength).map(DataDto::toDto).toList()
        );
    }
}