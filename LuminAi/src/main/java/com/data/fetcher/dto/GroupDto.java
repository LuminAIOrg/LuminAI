package com.data.fetcher.dto;

import java.util.List;

public record GroupDto(String groupName, List<SensorRecord> sensors) {
}
