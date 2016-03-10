package com.thorstiland.spielplan.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Game {
	@Id
	private UUID id;
	
	@com.fasterxml.jackson.annotation.JsonBackReference("Season.games")
    @ManyToOne
    private Season season;
	
	@ManyToOne
    private Team homeTeam;
	
	@ManyToOne
    private Team awayTeam;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	public Team getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}
	
	
	
}
