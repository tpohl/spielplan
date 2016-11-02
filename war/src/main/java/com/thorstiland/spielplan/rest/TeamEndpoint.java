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
import com.thorstiland.spielplan.service.UserService;

import io.swagger.annotations.Api;

@Api(tags = { "masterdata", "Team" })
@Named
@Stateless
@Path("team")
@Produces({ MediaType.APPLICATION_JSON })
public class TeamEndpoint {
	@Inject
	TeamService teamService;
	@Inject
	UserService userService;;
	@Inject TeamMapper teamMapper;
	
	@GET
    public List<TeamDto> getAll() {
        return teamMapper.toTeamDtos(teamService.findAll());
    }
	
	@GET
    @Path("/{id}")
    public TeamDto get(@PathParam("id") long id) {
        return teamMapper.toTeam(teamService.find(id));
    }
	
	@POST
    @Consumes({ MediaType.APPLICATION_JSON })
    public TeamDto post(TeamDto teamDto) {
		Team team = teamMapper.toEntity(teamDto);
        return teamMapper.toTeam(teamService.save(team));
    }
	
	@PUT
	@Path("/{id}")
    @Consumes({ MediaType.APPLICATION_JSON })
    public TeamDto put(@PathParam("id") long id,TeamDto teamDto) {
		Team team = teamService.find(id);
		team.setName(teamDto.getName());
		team.setUser(userService.find(teamDto.getUser().getId()));
		
        return teamMapper.toTeam(teamService.merge(team));
    }
	@PUT
	@Path("/{id}/user")
    @Consumes({ MediaType.APPLICATION_JSON })
    public TeamDto setUser(@PathParam("id") long id, String userId) {
		Team team = teamService.find(id);
		team.setUser(userService.find(userId));
		return teamMapper.toTeam(teamService.merge(team));
    }
	
	@DELETE
	@Path("/{id}")
    public void delete(@PathParam("id") long id) {
         teamService.delete(id);
    }
	
}
