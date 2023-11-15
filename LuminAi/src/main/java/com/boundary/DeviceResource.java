package com.boundary;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.json.Json;
import jakarta.ejb.Schedule;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import utils.com.DeviceUtils;


import java.io.File;
import java.net.URL;

@Path("/api/devices")
public class DeviceResource {
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        @Path("/getData")
        //@Schedule()
        public String getData(){
            try {
              return DeviceUtils.runDriver();
            }
            catch (Exception e){
                return 500 + e.getMessage();

            }
        }

}
