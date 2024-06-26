package com.data.dto;

import com.data.model.LatestUse;

public record LatestUseDto(Long id, String userId, SensorDto sensor, Long latestUse) {
    public static LatestUseDto toDto(LatestUse latestUse) {
        return new LatestUseDto(
                latestUse.getId(),
                latestUse.getUserId(),
                SensorDto.toDto(latestUse.getSensor(), 20),
                latestUse.getLatestUse()
        );
    }
}
