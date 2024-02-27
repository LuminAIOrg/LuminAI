package com.data.boundary;

import com.data.dto.PageDto;
import com.data.repository.SensorDataRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;

@Path("api/data")
public class DataResource {

    @Inject
    SensorDataRepository dataRepository;

    @GET
    @Path("/page/")
    public PageDto getSensorDataByPage(
            @DefaultValue("0") @QueryParam("pageNumber") int pageNumber,
            @DefaultValue("15") @QueryParam("limit") int limit
    ) {
        return dataRepository.getPage(pageNumber, limit);
    }
}
