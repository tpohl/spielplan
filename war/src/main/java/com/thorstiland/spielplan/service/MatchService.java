package com.thorstiland.spielplan.service;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Named;

import com.thorstiland.spielplan.model.Match;

@Named
@Stateless
//@RolesAllowed({ "admin", "user" })
public class MatchService extends CrudService<Match>{

	public MatchService() {
		super(Match.class);
	}

}
