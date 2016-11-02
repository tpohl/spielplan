package com.thorstiland.spielplan.service;

import java.util.ArrayList;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.ws.rs.PathParam;

import com.thorstiland.spielplan.dto.StandingsDto;
import com.thorstiland.spielplan.model.Community;
import com.thorstiland.spielplan.model.Match;
import com.thorstiland.spielplan.model.Season;
import com.thorstiland.spielplan.model.Team;

@Named
@Stateless
//@RolesAllowed({ "admin", "user" })
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
		
		return this.createMatches(s);
	}
	
	public Season createMatches(final Season season){
		// TODO make this better.
		for (final Team homeTeam : season.getTeams()) {
			for (final Team awayTeam : season.getTeams()) {
				if (homeTeam.getId()!=awayTeam.getId()){
					Match match = new Match();
					match.setHomeTeam(homeTeam);
					match.setAwayTeam(awayTeam);
					match.setSeason(season);
					this.entityManager.persist(match);
				}
			}
		}
		return this.save(season);
	}
	
	public StandingsDto getStandings( long id){
		// TODO implement me.
		return null;
	}
}
