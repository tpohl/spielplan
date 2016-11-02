package com.thorstiland.spielplan.rest;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thorstiland.spielplan.dto.MatchDto;
import com.thorstiland.spielplan.dto.SeasonDto;
import com.thorstiland.spielplan.dto.StandingsDto;
import com.thorstiland.spielplan.dto.TeamDto;
import com.thorstiland.spielplan.mapper.MatchMapper;
import com.thorstiland.spielplan.mapper.SeasonMapper;
import com.thorstiland.spielplan.model.Match;
import com.thorstiland.spielplan.model.Season;
import com.thorstiland.spielplan.model.Team;
import com.thorstiland.spielplan.service.SeasonService;
import com.thorstiland.spielplan.service.TeamService;

import io.swagger.annotations.Api;

@Api(tags = { "masterdata", "Season" })
@Named
@Stateless
@Path("season")
@Produces({ MediaType.APPLICATION_JSON })
public class SeasonEndpoint {

	private static final Logger LOG = LoggerFactory.getLogger(SeasonEndpoint.class);
	@Inject
	SeasonService seasonService;

	@Inject
	TeamService teamService;

	@Inject
	SeasonMapper seasonMapper;
	@Inject
	MatchMapper matchMapper;

	@GET
	public Collection<SeasonDto> getAll() {
		return seasonMapper.toDtos(seasonService.findAll());
	}

	@GET
	@Path("/{id}")
	public SeasonDto get(@PathParam("id") long id) {
		return seasonMapper.toDto(seasonService.find(id));
	}

	@GET
	@Path("/{id}/match")
	public List<MatchDto> getMatches(@PathParam("id") long id) {
		final Season season = seasonService.find(id);
		if (season != null) {
			List<Match> mathes = season.getMatches();
			return matchMapper.toDtos(mathes);

		} else {
			return null;
		}

	}

	@GET
	@Path("/{id}/standings")
	public StandingsDto getStandings(@PathParam("id") long id) {
		// TODO implement me.
		return null;
	}

	@PUT
	@Path("/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Season put(@PathParam("id") long id, SeasonDto seasonDto) {
		Season season = seasonService.find(id);
		season.setName(seasonDto.getName()); // TODO move this to a mapper.
		return seasonService.merge(season);
	}

	@GET
	@Path("/{id}/team")
	public Collection<Team> getTeams(@PathParam("id") long id) {
		// TODO implement
		return Collections.emptyList();
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/{id}/team")
	public Season addTeam(@PathParam("id") long id, TeamDto teamDto) {
		Season season = seasonService.find(id);
		Team team = teamService.find(teamDto.getId());
		season.getTeams().add(team);
		return seasonService.merge(season);
	}

	@DELETE
	@Path("/{id}/team/{teamId}")
	public Season removeTeam(@PathParam("id") long id, @PathParam("teamId") long teamId) {
		Season season = seasonService.find(id);
		for (Iterator<Team> iterator = season.getTeams().iterator(); iterator.hasNext();) {
			Team team = iterator.next();
			if (team.getId() == teamId) {
				iterator.remove();
				break;
			}

		}
		return seasonService.merge(season);
	}

	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") long id) {
		seasonService.delete(id);
	}
}
