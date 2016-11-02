package com.thorstiland.spielplan.rest;

import java.util.Collection;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.websocket.server.PathParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thorstiland.spielplan.dto.CommunityDto;
import com.thorstiland.spielplan.dto.MatchDto;
import com.thorstiland.spielplan.dto.SeasonDto;
import com.thorstiland.spielplan.dto.TeamDto;
import com.thorstiland.spielplan.dto.UserDto;
import com.thorstiland.spielplan.mapper.CommunityMapper;
import com.thorstiland.spielplan.mapper.MatchMapper;
import com.thorstiland.spielplan.mapper.SeasonMapper;
import com.thorstiland.spielplan.mapper.TeamMapper;
import com.thorstiland.spielplan.mapper.UserMapper;
import com.thorstiland.spielplan.model.Community;
import com.thorstiland.spielplan.model.Season;
import com.thorstiland.spielplan.model.User;
import com.thorstiland.spielplan.service.CommunityService;
import com.thorstiland.spielplan.service.MatchService;
import com.thorstiland.spielplan.service.UserService;

import io.swagger.annotations.Api;

@Api(tags = { "masterdata", "User" })
@Named
@Stateless
@Path("user")
@Produces({ MediaType.APPLICATION_JSON })

public class UserEndpoint {
	@Inject
	UserService userService;
	@Inject
	CommunityService communityService;
	@Inject
	MatchService matchService;

	@Inject
	UserMapper userMapper;
	@Inject
	TeamMapper teamMapper;
	@Inject
	CommunityMapper communityMapper;
	@Inject
	SeasonMapper seasonMapper;
	@Inject
	MatchMapper matchMapper;

	private static final Logger LOG = LoggerFactory.getLogger(UserEndpoint.class);

	@POST
	public UserDto createUser(UserDto userDto) {
		User u = userMapper.toEntity(userDto);
		return userMapper.toDto(userService.save(u));
	}

	@GET
	@Path("id/{id}/team")
	public Collection<TeamDto> getTeams(final @PathParam("id") String userId) {
		final User user = userService.find(userId);
		return teamMapper.toTeamDtos(user.getTeams());
	}

	@GET
	@Path("id/{id}/season")
	public Collection<SeasonDto> getSeasons(final @PathParam("id") String userId) {
		Collection<Season> seasons = userService.findCurrentSeasons(userId);
		return seasonMapper.toDtos(seasons);
	}

	@GET
	@Path("id/{id}/community")
	public Collection<CommunityDto> getCommunities(final @PathParam("id") String userId) {
		final User user = userService.find(userId);
		return communityMapper.toDtos(communityService.getByUser(user));
	}

	@GET
	@Path("id/{id}/nextmatches")
	public Collection<MatchDto> getNextMatches(final @PathParam("id") String userId) {
		return matchMapper.toDtos(matchService.findNextMatches(userId));
	}

	@RolesAllowed({ "user", "admin" })
	@GET
	@Path("/team")
	public Collection<TeamDto> getMyTeams(@Context SecurityContext sc) {
		final User user = loadUser(sc);
		return teamMapper.toTeamDtos(user.getTeams());
	}

	@RolesAllowed({ "user", "admin" })
	@GET
	@Path("/community")
	public Collection<CommunityDto> getMyCommunities(@Context SecurityContext sc) {
		final User user = loadUser(sc);
		return communityMapper.toDtos(communityService.getByUser(user));
	}

	@RolesAllowed({ "user", "admin" })
	@GET
	@Path("/season")
	public Collection<SeasonDto> getMySeasons(@Context SecurityContext sc) {
		final User user = loadUser(sc);
		Collection<Season> seasons = userService.findCurrentSeasons(user.getId());
		return seasonMapper.toDtos(seasons);
	}

	@RolesAllowed({ "user", "admin" })
	@GET
	@Path("/nextmatches")
	public Collection<MatchDto> getMyNextMatches(@Context SecurityContext sc) {
		final User user = loadUser(sc);
		return matchMapper.toDtos(matchService.findNextMatches(user.getId()));
	}

	protected User loadUser(SecurityContext sc) {
		final User user = userService.load(sc.getUserPrincipal().getName());
		return user;
	}
}
