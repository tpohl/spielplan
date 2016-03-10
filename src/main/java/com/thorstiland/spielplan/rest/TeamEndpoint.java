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

import com.thorstiland.spielplan.model.Team;
import com.thorstiland.spielplan.service.TeamService;

import io.swagger.annotations.Api;

@Api(tags = { "masterdata", "Team" })
@Named
@Stateless
@Path("team")
public class TeamEndpoint {
	@Inject
	TeamService TeamService;
	
	@GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/{id}")
    public Team get(@PathParam("id") UUID id) {
        return TeamService.find(id);
    }
	
	@POST
    @Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
    public Team post(Team Team) {
        return TeamService.save(Team);
    }
	
	@PUT
	@Path("/{id}")
    @Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
    public Team put(@PathParam("id") UUID id,Team Team) {
		Team.setId(id);
        return TeamService.merge(Team);
    }
	
	@DELETE
	@Path("/{id}")
    public void delete(@PathParam("id") UUID id) {
         TeamService.delete(id);
    }
}
