package com.data.boundary;


import com.data.spi.ServiceInstance;
import com.data.spi.ServiceLoader;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import java.util.List;
import java.util.Set;

@Path("api/datacollection")
public class DataCollectionMethodResource {
    @Inject
    ServiceLoader serviceLoader;

    @POST
    @RolesAllowed("user")
    @Path("load")
    public void loadDataCollectionMethod(ServiceNameWrapper serviceName) {
        serviceLoader.startService(serviceName.serviceName());
    }

    @POST
    @RolesAllowed("user")
    @Path("delete/{instanceId}")
    public boolean deleteInstance(@PathParam("instanceId") int instanceId) {
        return serviceLoader.revokeInstace(instanceId);
    }

    @POST
    @RolesAllowed("user")
    @Path("disable/{instanceId}")
    public ServiceInstance disableInstance(@PathParam("instanceId") int instanceId) {
        return serviceLoader.disableInstance(instanceId);
    }

    @POST
    @RolesAllowed("user")
    @Path("enable/{instanceId}")
    public ServiceInstance enableInstance(@PathParam("instanceId") int instanceId) {
        return serviceLoader.enableInstance(instanceId);
    }

    @GET
    @RolesAllowed("user")
    @Path("list")
    public List<String> listAllDataCollectionMethods() {
        return serviceLoader.getAllServices();
    }

    @GET
    @RolesAllowed("user")
    @Path("serviceInstances")
    public Set<ServiceInstance> debug() {
        return serviceLoader.getAllServiceInstances();
    }
}

record ServiceNameWrapper(String serviceName) {
}
