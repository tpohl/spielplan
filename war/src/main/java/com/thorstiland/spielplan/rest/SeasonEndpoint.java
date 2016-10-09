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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thorstiland.spielplan.dto.GameDto;
import com.thorstiland.spielplan.dto.SeasonDto;
import com.thorstiland.spielplan.mapper.GameMapper;
import com.thorstiland.spielplan.mapper.SeasonMapper;
import com.thorstiland.spielplan.model.Game;
import com.thorstiland.spielplan.model.Season;
import com.thorstiland.spielplan.service.SeasonService;

import io.swagger.annotations.Api;

@Api(tags = { "masterdata", "Season" })
@Named
@Stateless
@Path("season")
public class SeasonEndpoint {
	
	private static final Logger LOG = LoggerFactory.getLogger(SeasonEndpoint.class);
	@Inject
	SeasonService seasonService;

	@Inject
	SeasonMapper seasonMapper;
	@Inject
	GameMapper gameMapper;
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<SeasonDto> getAll() {
		return seasonMapper.toDtos(seasonService.findAll());
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{id}")
	public SeasonDto get(@PathParam("id") long id) {
		return seasonMapper.toDto(seasonService.find(id));
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
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

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Season post(Season season) {
		LOG.info("Creating Season {}", season);
		return seasonService.save(season);
	}

	@PUT
	@Path("/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Season put(@PathParam("id") long id, Season season) {
		season.setId(id);
		return seasonService.merge(season);
	}

	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") long id) {
		seasonService.delete(id);
	}
}
