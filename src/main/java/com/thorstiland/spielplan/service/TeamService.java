package com.thorstiland.spielplan.service;

import javax.inject.Named;

import com.thorstiland.spielplan.model.Team;

@Named
public class TeamService extends CrudService<Team>{

	public TeamService() {
		super(Team.class);
	}

}
