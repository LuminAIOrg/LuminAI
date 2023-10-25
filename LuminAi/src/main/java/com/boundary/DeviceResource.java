package com.boundary;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.json.Json;
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
        public String getData(){

                URL scriptPath = DeviceResource.class.getResource("drivers/divers.py");

                System.out.println(scriptPath);

            try {
                DeviceUtils.runScript(scriptPath);
            }
            catch (Exception e){
                return 500 + e.getMessage();
            }

                //TODO: map json file to object
            try {
                ObjectMapper objectMapper = new ObjectMapper();

                URL filePath = DeviceResource.class.getResource("/drivers/energy_data.json");

                Object object = objectMapper.readValue(filePath, Object.class);
                return objectMapper.writeValueAsString(object);
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return null;
        }

}
