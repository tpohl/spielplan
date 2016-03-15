package com.thorstiland.spielplan.service;

import java.util.ArrayList;

import javax.inject.Named;

import com.thorstiland.spielplan.model.Community;
import com.thorstiland.spielplan.model.Game;
import com.thorstiland.spielplan.model.Season;
import com.thorstiland.spielplan.model.Team;

@Named
public class SeasonService extends CrudService<Season>{

	public SeasonService() {
		super(Season.class);
	}

	public Season createSeasonForCommunity(String name, Community community){
		Season s = new Season();
		s.setTeams(new ArrayList<>(community.getTeams()));
		s.setName(name);
		s.setCommunity(community);
		s = this.save(s);
		
		return this.createGames(s);
	}
	
	public Season createGames(final Season season){
		for (final Team homeTeam : season.getTeams()) {
			for (final Team awayTeam : season.getTeams()) {
				if (homeTeam.getId()!=awayTeam.getId()){
					Game game = new Game();
					game.setHomeTeam(homeTeam);
					game.setAwayTeam(awayTeam);
					game.setSeason(season);
					this.entityManager.persist(game);
				}
			}
		}
		return this.save(season);
	}
}
