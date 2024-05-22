package com.data.boundary;

import com.data.model.LatestUse;
import com.data.repository.LatestUseRepository;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

import java.util.List;

@Path("api/latest-use")
public class LatestUseResource {
    @Inject
    LatestUseRepository latestUseRepository;

    @GET
    @RolesAllowed("user")
    public List<LatestUse> getLatestUseByUser(){
        return latestUseRepository.getAllLatestUseOfUser();
    }

    @POST
    @RolesAllowed("user")
    @Path("/use/{sensorId}")
    public LatestUse addNewLatestUse(@PathParam("sensorId") long sensorId){
        return latestUseRepository.addLatestUse(sensorId);
    }

}
