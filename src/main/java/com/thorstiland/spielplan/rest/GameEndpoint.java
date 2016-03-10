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

import com.thorstiland.spielplan.model.Game;
import com.thorstiland.spielplan.service.GameService;

import io.swagger.annotations.Api;

@Api(tags = { "masterdata", "Game" })
@Named
@Stateless
@Path("Game")
public class GameEndpoint {
	@Inject
	GameService GameService;
	
	@GET
    @Produces({ MediaType.APPLICATION_JSON })
    @Path("/{id}")
    public Game get(@PathParam("id") UUID id) {
        return GameService.find(id);
    }
	
	@POST
    @Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
    public Game post(Game Game) {
        return GameService.save(Game);
    }
	
	@PUT
	@Path("/{id}")
    @Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
    public Game put(@PathParam("id") UUID id,Game Game) {
		Game.setId(id);
        return GameService.merge(Game);
    }
	
	@DELETE
	@Path("/{id}")
    public void delete(@PathParam("id") UUID id) {
         GameService.delete(id);
    }
}
