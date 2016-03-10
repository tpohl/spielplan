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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.thorstiland.spielplan.model.Game;
import com.thorstiland.spielplan.service.GameService;

import io.swagger.annotations.Api;

@Api(tags = { "masterdata", "Game" })
@Named
@Stateless
@Path("Game")
public class GameEndpoint {
	@Inject
	GameService gameService;
	
	@GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/{id}")
    public Game get(@PathParam("id") UUID id) {
        return gameService.find(id);
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
    public Game put(@PathParam("id") UUID id,Game Game) {
		Game.setId(id);
        return gameService.merge(Game);
    }
	
	@DELETE
	@Path("/{id}")
    public void delete(@PathParam("id") UUID id) {
         gameService.delete(id);
    }
	
	@POST
	@Path("/score/{id}")
	public void saveScore(@PathParam("id") UUID id, @QueryParam("home") int homeScore, @QueryParam("away") int awayScore){
		Game game = gameService.find(id);
		game.setScoreHome(homeScore);
		game.setScoreAway(awayScore);
		gameService.merge(game);
	}
}
