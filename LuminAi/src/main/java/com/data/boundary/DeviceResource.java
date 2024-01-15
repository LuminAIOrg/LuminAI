package com.data.boundary;

import com.data.model.SensorData;
import com.data.repository.SensorDataRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;


@ApplicationScoped
@Path("/api/devices")
public class DeviceResource {
        @Inject
        SensorDataRepository sensorDataRepository;

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        @Path("/getData")
        public List<SensorData> getData(){
            return sensorDataRepository.getAllData();
        }

}
