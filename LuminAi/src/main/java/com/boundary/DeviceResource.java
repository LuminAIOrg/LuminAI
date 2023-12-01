package com.boundary;

import com.model.Data;
import com.repository.DataRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;


@ApplicationScoped
@Path("/api/devices")
public class DeviceResource {
        @Inject
        DataRepository dataRepository;

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        @Path("/getData")
        public List<Data> getData(){
            return dataRepository.getAllData();
        }

}
