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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thorstiland.spielplan.dto.CommunityDto;
import com.thorstiland.spielplan.dto.CommunityBasicDto;
import com.thorstiland.spielplan.dto.SeasonDto;
import com.thorstiland.spielplan.dto.TeamDto;
import com.thorstiland.spielplan.mapper.CommunityMapper;
import com.thorstiland.spielplan.mapper.SeasonMapper;
import com.thorstiland.spielplan.mapper.TeamMapper;
import com.thorstiland.spielplan.model.Community;
import com.thorstiland.spielplan.model.Season;
import com.thorstiland.spielplan.model.Team;
import com.thorstiland.spielplan.service.CommunityService;
import com.thorstiland.spielplan.service.SeasonService;
import com.thorstiland.spielplan.service.TeamService;

import io.swagger.annotations.Api;

@Api(tags = { "masterdata", "Community" })
@Named
@Stateless
@Path("community")
@Produces({ MediaType.APPLICATION_JSON })
public class CommunityEndpoint {

	private static final Logger LOG = LoggerFactory.getLogger(CommunityEndpoint.class);
	@Inject
	CommunityMapper communityMapper;
	@Inject
	SeasonMapper seasonMapper;
	@Inject
	TeamMapper teamMapper;

	@Inject
	CommunityService communityService;

	@Inject
	TeamService teamService;

	@Inject
	SeasonService seasonService;

	@GET
	public List<CommunityDto> getAll() {
		return communityMapper.toDtos(communityService.findAll());
	}

	@GET
	@Path("/{id}")
	public CommunityDto get(@PathParam("id") long id) {
		return communityMapper.toDto(communityService.find(id));
	}

	@GET
	@Path("/{id}/team")
	public List<TeamDto> getTeams(@PathParam("id") long id) {
		List<Team> teams = communityService.find(id).getTeams();
		return teamMapper.toTeamDtos(teams);
	}

	@GET
	@Path("/{id}/season")
	public List<SeasonDto> getSeasons(@PathParam("id") long id) {
		List<Season> seasons = communityService.find(id).getSeasons();
		return seasonMapper.toDtos(seasons);
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public CommunityDto post(CommunityBasicDto communityDto) {
		LOG.info("Creating Community {}", communityDto);
		Community community = communityMapper.toEntity(communityDto);
		return communityMapper.toDto(communityService.save(community));
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/{id}/addTeam")
	public CommunityDto addTeam(@PathParam("id") long id, TeamDto teamDto) {
		final Community c = communityService.find(id);
		final Team team = teamService.find(teamDto.getId());
		LOG.info("Adding Team {} to  Community {}", team, c);
		return communityMapper.toDto(communityService.addTeam(c, team));
	}

	@PUT
	@Path("/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Community put(@PathParam("id") long id, Community community) {
		community.setId(id);
		return communityService.merge(community);
	}

	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") long id) {
		communityService.delete(id);
	}

	@POST
	@Path("/{id}/season")
	public SeasonDto createSeasonForCommunity(@PathParam("id") long communityId,
			@QueryParam("seasonName") String seasonName) {
		Community c = communityService.find(communityId);
		if (c != null) {
			return seasonMapper.toDto(seasonService.createSeasonForCommunity(seasonName, c));
		} else {
			return null;
		}
	}
}
