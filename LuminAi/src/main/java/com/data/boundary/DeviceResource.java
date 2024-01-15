package com.data.boundary;

import com.data.fetcher.mqtt.MqttConnection;
import com.data.model.Data;
import com.data.repository.DataRepository;
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
        DataRepository dataRepository;

        @Inject
        MqttConnection mqttConnection;

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        @Path("/getData")
        public List<Data> getData(){
            return dataRepository.getAllData();
        }

        @GET
        @Path("/startup")
        public void startUp(){
                mqttConnection.invoke();
        }

}
