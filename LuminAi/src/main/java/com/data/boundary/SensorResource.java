package com.data.boundary;

import com.data.dto.SensorWithoutDataDto;
import com.data.repository.SensorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;


@ApplicationScoped
@Path("/api/sensor")
public class SensorResource {
    @Inject
    SensorRepository sensorRepository;

    @GET
    @Path("list")
    public List<SensorWithoutDataDto> getAllSensors() {
        return sensorRepository.getAllSensors();
    }
}
