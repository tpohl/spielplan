package com.thorstiland.spielplan.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Community {
	@Id
	@GeneratedValue
	long id;

	private String name;

	@ManyToMany(targetEntity = Team.class, fetch = FetchType.EAGER)
	@JoinTable(name = "community2team")
	private List<Team> teams;

	@OneToMany(mappedBy = "community", cascade = CascadeType.REMOVE)
	private List<Season> seasons;

}
