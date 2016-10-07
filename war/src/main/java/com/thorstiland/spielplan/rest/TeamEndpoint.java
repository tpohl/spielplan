package com.thorstiland.spielplan.rest;

import java.util.List;

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

import com.thorstiland.spielplan.dto.TeamDto;
import com.thorstiland.spielplan.mapper.TeamMapper;
import com.thorstiland.spielplan.model.Team;
import com.thorstiland.spielplan.service.TeamService;

import io.swagger.annotations.Api;

@Api(tags = { "masterdata", "Team" })
@Named
@Stateless
@Path("team")
public class TeamEndpoint {
	@Inject
	TeamService teamService;
	@Inject TeamMapper teamMapper;
	
	@GET
    @Produces({ MediaType.APPLICATION_JSON }) 
    public List<TeamDto> getAll() {
        return teamMapper.toTeamDtos(teamService.findAll());
    }
	
	@GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/{id}")
    public TeamDto get(@PathParam("id") long id) {
        return teamMapper.toTeam(teamService.find(id));
    }
	
	@POST
    @Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
    public Team post(Team Team) {
        return teamService.save(Team);
    }
	
	@PUT
	@Path("/{id}")
    @Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
    public Team put(@PathParam("id") long id,Team Team) {
		Team.setId(id);
        return teamService.merge(Team);
    }
	
	@DELETE
	@Path("/{id}")
    public void delete(@PathParam("id") long id) {
         teamService.delete(id);
    }
	
}
