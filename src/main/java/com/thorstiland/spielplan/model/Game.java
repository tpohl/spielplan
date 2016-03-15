package com.thorstiland.spielplan.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Game {
	@Id @GeneratedValue long id;
	
	private int scoreHome;
	private int scoreAway;
	
	@com.fasterxml.jackson.annotation.JsonBackReference("Season.games")
    @ManyToOne
    private Season season;
	
	@ManyToOne(fetch=FetchType.EAGER)
    private Team homeTeam;
	
	@ManyToOne(fetch=FetchType.EAGER)
    private Team awayTeam;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public int getScoreHome() {
		return scoreHome;
	}

	public void setScoreHome(int scoreHome) {
		this.scoreHome = scoreHome;
	}

	public int getScoreAway() {
		return scoreAway;
	}

	public void setScoreAway(int scoreAway) {
		this.scoreAway = scoreAway;
	}
	
	
	
}
