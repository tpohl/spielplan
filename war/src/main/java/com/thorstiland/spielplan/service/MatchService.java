package com.thorstiland.spielplan.service;

import java.util.Collections;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;

import com.thorstiland.spielplan.model.Match;

@Named
@Stateless
public class MatchService extends CrudService<Match> {

	public MatchService() {
		super(Match.class);
	}

	public List<Match> findNextMatches(String userId) {
		return this.findNextMatches(userId, 5);
	}

	public List<Match> findNextMatches(String userId, int numberOfMatches) {
		// TODO Implement;
		return Collections.emptyList();
	}
}
