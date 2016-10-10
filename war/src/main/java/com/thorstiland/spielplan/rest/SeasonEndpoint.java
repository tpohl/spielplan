package com.thorstiland.spielplan.rest;

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

import com.thorstiland.spielplan.dto.GameDto;
import com.thorstiland.spielplan.dto.SeasonDto;
import com.thorstiland.spielplan.dto.StandingsDto;
import com.thorstiland.spielplan.dto.TeamDto;
import com.thorstiland.spielplan.mapper.GameMapper;
import com.thorstiland.spielplan.mapper.SeasonMapper;
import com.thorstiland.spielplan.model.Game;
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
	GameMapper gameMapper;

	@GET
	public List<SeasonDto> getAll() {
		return seasonMapper.toDtos(seasonService.findAll());
	}

	@GET
	@Path("/{id}")
	public SeasonDto get(@PathParam("id") long id) {
		return seasonMapper.toDto(seasonService.find(id));
	}

	@GET
	@Path("/{id}/game")
	public List<GameDto> getGames(@PathParam("id") long id) {
		final Season season = seasonService.find(id);
		if (season != null) {
			List<Game> games = season.getGames();
			return gameMapper.toGameDtos(games);

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

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/{id}/add-team")
	public Season addTeam(@PathParam("id") long id, TeamDto teamDto) {
		Season season = seasonService.find(id);
		Team team = teamService.find(teamDto.getId());
		season.getTeams().add(team);
		return seasonService.merge(season);
	}

	@DELETE
	@Path("/{id}/teams/{teamId}")
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
