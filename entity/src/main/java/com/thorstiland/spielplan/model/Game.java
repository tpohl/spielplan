package com.thorstiland.spielplan.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
@Entity
@Data
public class Game {
	@Id
	@GeneratedValue
	long id;

	private Integer scoreHome;
	private Integer scoreAway;
	@Temporal(TemporalType.TIMESTAMP)
	private Date playDate;

	@ManyToOne
	private Season season;

	@ManyToOne(fetch = FetchType.EAGER)
	private Team homeTeam;

	@ManyToOne(fetch = FetchType.EAGER)
	private Team awayTeam;



}
