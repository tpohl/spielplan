package com.thorstiland.spielplan.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Game {
	@Id
	@GeneratedValue
	long id;

	private Integer scoreHome;
	private Integer scoreAway;
	@Temporal(TemporalType.TIMESTAMP)
	private Date playDate;

	@com.fasterxml.jackson.annotation.JsonBackReference("Season.games")
	@ManyToOne
	private Season season;

	@ManyToOne(fetch = FetchType.EAGER)
	private Team homeTeam;

	@ManyToOne(fetch = FetchType.EAGER)
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

	public Integer getScoreHome() {
		return scoreHome;
	}

	public void setScoreHome(Integer scoreHome) {
		this.scoreHome = scoreHome;
	}

	public Integer getScoreAway() {
		return scoreAway;
	}

	public void setScoreAway(Integer scoreAway) {
		this.scoreAway = scoreAway;
	}

	public Date getPlayDate() {
		return playDate;
	}

	public void setPlayDate(Date playDate) {
		this.playDate = playDate;
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", scoreHome=" + scoreHome + ", scoreAway=" + scoreAway + ", playDate=" + playDate
				+ ", homeTeam=" + homeTeam + ", awayTeam=" + awayTeam + "]";
	}

}
