package com.data.boundary;

import com.data.dto.DataDto;
import com.data.dto.PageDto;
import com.data.repository.SensorDataRepository;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

import java.util.List;

@Path("api/data")
public class DataResource {

    @Inject
    SensorDataRepository dataRepository;

    @GET
    @RolesAllowed("user")
    @Path("/page/")
    public PageDto getSensorDataByPage(
            @DefaultValue("0") @QueryParam("pageNumber") int pageNumber,
            @DefaultValue("15") @QueryParam("limit") int limit
    ) {
        return dataRepository.getPage(pageNumber, limit);
    }

    @GET
    @Path("/inTimeframe/{id}")
    public List<DataDto> getDataBySensorInTimeframe(
            @PathParam("id") long sensorId,
            @QueryParam("from") long from,
            @QueryParam("to") long to
    ) {
        return dataRepository.getDataBySensorInTimeframe(sensorId, from, to);
    }
}
