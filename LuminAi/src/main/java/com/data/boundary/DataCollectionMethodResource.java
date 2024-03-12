package com.data.boundary;


import com.data.spi.ServiceLoader;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("api/datacollection")
public class DataCollectionMethodResource {
    @Inject
    ServiceLoader serviceLoader;
    
    @POST
    @Path("load")
    public void loadDataCollectionMethod(ServiceNameWrapper serviceName) {
        serviceLoader.loadService(serviceName.serviceName());
    }
}

record ServiceNameWrapper(String serviceName){}
