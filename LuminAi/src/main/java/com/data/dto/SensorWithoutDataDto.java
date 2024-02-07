package com.data.dto;

import com.data.model.Sensor;

public record SensorWithoutDataDto(Long id, String name, String unit) {
    public static SensorWithoutDataDto toDto(Sensor sensor) {
        return new SensorWithoutDataDto(sensor.getId(), sensor.getName(), sensor.getUnit());
    }
}
