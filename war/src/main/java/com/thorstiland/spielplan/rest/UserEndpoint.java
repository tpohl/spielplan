package com.thorstiland.spielplan.rest;

import java.util.Collection;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thorstiland.spielplan.model.Community;
import com.thorstiland.spielplan.model.Match;
import com.thorstiland.spielplan.model.Season;
import com.thorstiland.spielplan.model.Team;

import io.swagger.annotations.Api;

@Api(tags = { "masterdata", "User" })
@Named
@Stateless
@Path("user")
@Produces({ MediaType.APPLICATION_JSON })
@RolesAllowed({"user", "admin"})
public class UserEndpoint {

	private static final Logger LOG = LoggerFactory.getLogger(UserEndpoint.class);

	@GET
	@Path("/team")
	public Collection<Team> getMyTeams(@Context SecurityContext sc) {
		return null;
	}

	@GET
	@Path("/community")
	public Collection<Community> getMyCommunities(@Context SecurityContext sc) {
		return null;
	}

	@GET
	@Path("/season")
	public Collection<Season> getMySeasons(@Context SecurityContext sc) {
		return null;
	}

	@GET
	@Path("/nextmatches")
	public Collection<Match> getMyNextMatches(@Context SecurityContext sc) {
		return null;
	}
}
