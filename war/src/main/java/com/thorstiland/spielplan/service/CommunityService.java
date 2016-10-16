package com.thorstiland.spielplan.service;

import java.util.ArrayList;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import com.thorstiland.spielplan.model.Community;
import com.thorstiland.spielplan.model.Team;

@Named
@Stateless
//@RolesAllowed({ "admin", "user" })
public class CommunityService extends CrudService<Community> {

	@Inject
	TeamService teamService;

	public CommunityService() {
		super(Community.class);
	}

	public Community addTeam(Community c, Team team) {

		team = teamService.find(team.getId());

		if (c.getTeams() == null) {
			c.setTeams(new ArrayList<>(1));
		}
		c.getTeams().add(team);
		this.merge(c);
		return c;

	}

}
