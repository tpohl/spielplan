package com.thorstiland.spielplan.service;

import java.util.ArrayList;

import javax.inject.Named;

import com.thorstiland.spielplan.model.Community;
import com.thorstiland.spielplan.model.Team;

@Named
public class CommunityService extends CrudService<Community>{

	public CommunityService() {
		super(Community.class);
	}

	public Community addTeam(Community c, Team team) {
		team = this.entityManager.merge(team);
		if (c.getTeams() == null){
			c.setTeams(new ArrayList<>(1));
		}
		c.getTeams().add(team);
		this.merge(c);
		return c;
		
	}

}
