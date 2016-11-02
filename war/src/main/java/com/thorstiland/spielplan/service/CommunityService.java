package com.thorstiland.spielplan.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import com.thorstiland.spielplan.model.Community;
import com.thorstiland.spielplan.model.Season;
import com.thorstiland.spielplan.model.Team;
import com.thorstiland.spielplan.model.User;

@Named
@Stateless
// @RolesAllowed({ "admin", "user" })
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

	public List<Community> getByUser(User user) {
		List<Community> results = (List<Community>) entityManager
				.createQuery("SELECT c FROM Community c WHERE c.teams.user=:user").setParameter("user", user)
				.getResultList();
		return results;
	}

	public Season findCurrentSeason(Serializable communityId) {
		return this.find(communityId).getSeasons().get(0);
	}

}
