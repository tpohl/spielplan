package com.thorstiland.spielplan.rest;

import java.util.UUID;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.thorstiland.spielplan.model.Season;
import com.thorstiland.spielplan.service.SeasonService;

import io.swagger.annotations.Api;

@Api(tags = { "masterdata", "Season" })
@Named
@Stateless
@Path("season")
public class SeasonEndpoint {
	@Inject
	SeasonService seasonService;
	
	@GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/{id}")
    public Season get(@PathParam("id") UUID id) {
        return seasonService.find(id);
    }
	
	@POST
    @Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
    public Season post(Season season) {
        return seasonService.save(season);
    }
	
	@PUT
	@Path("/{id}")
    @Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
    public Season put(@PathParam("id") UUID id,Season season) {
		season.setId(id);
        return seasonService.merge(season);
    }
	
	
	@DELETE
	@Path("/{id}")
    public void delete(@PathParam("id") UUID id) {
         seasonService.delete(id);
    }
}
