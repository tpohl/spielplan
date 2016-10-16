package com.thorstiland.spielplan.service;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Named;

import com.thorstiland.spielplan.model.Team;

@Named
@Stateless
//@RolesAllowed({ "admin", "user" })
public class TeamService extends CrudService<Team>{

	public TeamService() {
		super(Team.class);
	}

}
