package com.data.boundary;


import com.data.spi.ServiceLoader;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("api/datacollection")
public class DataCollectionMethodResource {
    @Inject
    ServiceLoader serviceLoader;
    
    @POST
    @RolesAllowed("user")
    @Path("load")
    public void loadDataCollectionMethod(ServiceNameWrapper serviceName) {
        serviceLoader.loadService(serviceName.serviceName());
    }

    @GET
    @RolesAllowed("user")
    @Path("list")
    public List<String> listAllDataCollectionMethods() {
        return serviceLoader.getAllServices();
    }

    @GET
    @RolesAllowed("user")
    @Path("current")
    public String getCurrentDataCollectionMethod() {
        return serviceLoader.getActiveService();
    }
}

record ServiceNameWrapper(String serviceName){}
