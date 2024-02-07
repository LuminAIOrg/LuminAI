package com.data.dto;

import java.util.List;

//The from is the timestamp of the minimum value of the page
public record PageDto(long pageNumber, List<SensorDto> sensors) {
}
