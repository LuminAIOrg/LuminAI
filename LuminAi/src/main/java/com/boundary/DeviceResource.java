package com.boundary;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import com.utils.DeviceUtils;

@ApplicationScoped
@Path("/api/devices")
public class DeviceResource {
        @ConfigProperty(name = "apiUrl")
        String apiUrl;

        @GET
        @Produces(MediaType.APPLICATION_JSON)
        @Path("/getData")
        //@Schedule()
        public String getData(){
                return DeviceUtils.runDriver(apiUrl);
        }

}
