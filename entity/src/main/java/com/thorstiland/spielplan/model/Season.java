package com.thorstiland.spielplan.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Season {
	@Id
	@GeneratedValue
	long id;

	private String name;

	@ManyToOne
	private Community community;

	@ManyToMany(targetEntity = Team.class, fetch = FetchType.EAGER)
	@JoinTable(name = "season2team")
	private List<Team> teams;

	@OneToMany(mappedBy = "season", cascade = CascadeType.REMOVE)
	private List<Match> matches;

}
