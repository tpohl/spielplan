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

import com.thorstiland.spielplan.dto.GameDto;
import com.thorstiland.spielplan.mapper.GameMapper;
import com.thorstiland.spielplan.model.Game;
import com.thorstiland.spielplan.service.GameService;

import io.swagger.annotations.Api;

@Api(tags = { "masterdata", "Game" })
@Named
@Stateless
@Path("game")
public class GameEndpoint {
	@Inject
	GameService gameService;
	@Inject
	GameMapper gameMapper;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<GameDto> getAll() {
		return gameMapper.toGameDtos(gameService.findAll());
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{id}")
	public GameDto get(@PathParam("id") long id) {
		return gameMapper.toGameDto(gameService.find(id));
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Game post(Game Game) {
		return gameService.save(Game);
	}

	@PUT
	@Path("/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Game put(@PathParam("id") long id, Game Game) {
		Game.setId(id);
		return gameService.merge(Game);
	}

	@DELETE
	@Path("/{id}")
	public void delete(@PathParam("id") long id) {
		gameService.delete(id);
	}

	@POST
	@Path("/score/{id}")
	public void saveScore(@PathParam("id") long id, @QueryParam("home") int homeScore,
			@QueryParam("away") int awayScore) {
		Game game = gameService.find(id);
		game.setScoreHome(homeScore);
		game.setScoreAway(awayScore);
		gameService.merge(game);
	}
}
