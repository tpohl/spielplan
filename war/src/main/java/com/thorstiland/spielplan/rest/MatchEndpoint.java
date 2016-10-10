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

import com.thorstiland.spielplan.dto.MatchDto;
import com.thorstiland.spielplan.mapper.MatchMapper;
import com.thorstiland.spielplan.model.Match;
import com.thorstiland.spielplan.service.MatchService;

import io.swagger.annotations.Api;

@Api(tags = { "masterdata", "Match" })
@Named
@Stateless
@Path("match")
public class MatchEndpoint {
	@Inject
	MatchService matchService;
	@Inject
	MatchMapper matchMapper;

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<MatchDto> getAll() {
		return matchMapper.toDtos(matchService.findAll());
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{id}")
	public MatchDto get(@PathParam("id") long id) {
		return matchMapper.toDto(matchService.find(id));
	}

//	@POST
//	@Consumes({ MediaType.APPLICATION_JSON })
//	@Produces({ MediaType.APPLICATION_JSON })
//	public Match post(Match Game) {
//		return matchService.save(Game);
//	}

//	@PUT
//	@Path("/{id}")
//	@Consumes({ MediaType.APPLICATION_JSON })
//	@Produces({ MediaType.APPLICATION_JSON })
//	public Match put(@PathParam("id") long id, MatchDto matchDto) {
//		Match match = matchService.find(id);
//		// TODO merge
//		return matchService.save(match);
//	}

//	@DELETE
//	@Path("/{id}")
//	public void delete(@PathParam("id") long id) {
//		matchService.delete(id);
//	}

	@POST
	@Path("/score/{id}")
	public void saveScore(@PathParam("id") long id, @QueryParam("home") int homeScore,
			@QueryParam("away") int awayScore) {
		Match match = matchService.find(id);
		match.setScoreHome(homeScore);
		match.setScoreAway(awayScore);
		matchService.merge(match);
	}
}
