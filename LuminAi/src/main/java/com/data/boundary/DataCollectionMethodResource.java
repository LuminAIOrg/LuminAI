package com.data.boundary;


import com.data.spi.ServiceLoader;
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
    @Path("load")
    public void loadDataCollectionMethod(ServiceNameWrapper serviceName) {
        serviceLoader.loadService(serviceName.serviceName());
    }

    @GET
    @Path("list")
    public List<String> listAllDataCollectionMethods() {
        return serviceLoader.getAllServices();
    }

    @GET
    @Path("current")
    public String getCurrentDataCollectionMethod() {
        return serviceLoader.getActiveService();
    }
}

record ServiceNameWrapper(String serviceName){}
