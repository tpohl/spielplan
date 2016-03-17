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

import com.fasterxml.jackson.annotation.JsonView;
import com.thorstiland.spielplan.model.Community;
import com.thorstiland.spielplan.model.Season;
import com.thorstiland.spielplan.model.Team;
import com.thorstiland.spielplan.model.jsonview.Views;
import com.thorstiland.spielplan.service.CommunityService;
import com.thorstiland.spielplan.service.SeasonService;

import io.swagger.annotations.Api;

@Api(tags = { "masterdata", "Community" })
@Named
@Stateless
@Path("community")
public class CommunityEndpoint {
	
	private static final Logger LOG = LoggerFactory.getLogger(CommunityEndpoint.class);
	@Inject
	CommunityService communityService;

	@Inject
	SeasonService seasonService;
	
	@JsonView(Views.Basic.class)
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Community> getAll() {
		return communityService.findAll();
	}

	@JsonView(Views.Basic.class)
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{id}")
	public Community get(@PathParam("id") long id) {
		return communityService.find(id);
	}
	
	@JsonView(Views.Basic.class)
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{id}/teams")
	public List<Team> getTeams(@PathParam("id") long id) {
		List<Team> teams =  communityService.find(id).getTeams();
		teams.size();
		return teams;
	}
	
	@JsonView(Views.Basic.class)
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{id}/seasons")
	public List<Season> getSeasons(@PathParam("id") long id) {
		List<Season> seasons =  communityService.find(id).getSeasons();
		seasons.size();
		return seasons;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public Community post(Community community) {
		LOG.info("Creating Community {}", community);
		return communityService.save(community);
	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{id}/addTeam")
	public Community addTeam(@PathParam("id") long id,Team team) {
		Community c = communityService.find(id);
		LOG.info("Adding Team {} to  Community {}", team, c);		
		return communityService.addTeam(c, team);
	}
	
	@JsonView(Views.Basic.class)
	@PUT
	@Path("/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
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
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{id}/createSeason")
	public Season createSeasonForCommunity(@PathParam("id") long communityId,
			@QueryParam("seasonName") String seasonName) {
		Community c = communityService.find(communityId);
		if (c != null) {
			return seasonService.createSeasonForCommunity(seasonName, c);
		} else {
			return null;
		}
	}
}
