package com.thorstiland.spielplan.service;

import javax.inject.Named;

import com.thorstiland.spielplan.model.Match;

@Named
public class MatchService extends CrudService<Match>{

	public MatchService() {
		super(Match.class);
	}

}
