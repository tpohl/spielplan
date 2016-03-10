package com.thorstiland.spielplan.rest;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.thorstiland.spielplan.model.Team;

import io.swagger.annotations.Api;

@Api(tags = { "masterdata", "Team" })
@Named
@Stateless
@Path("team")
public class TeamEndpoint {
	@GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/{id}")
    public Team get(@PathParam("id") long id) {
        Team team = new Team();
        team.setName("Team "+id);
        return team;
    }
}
