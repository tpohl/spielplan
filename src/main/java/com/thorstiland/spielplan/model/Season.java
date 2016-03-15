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

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.thorstiland.spielplan.model.jsonview.Views.Hierarchy;
@Entity
public class Season {
	@Id @GeneratedValue long id;
	
	private String name;
	
	@com.fasterxml.jackson.annotation.JsonBackReference("Community.seasons")
    @ManyToOne
	private Community community;
	
	@ManyToMany(targetEntity = Team.class, fetch=FetchType.EAGER)
    @JoinTable(name="season2team")
	private List<Team> teams;
	
	@JsonView(Hierarchy.class)
	@JsonManagedReference("Season.games")
	@OneToMany(mappedBy = "season", cascade=CascadeType.REMOVE)
	private List<Game> games;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Team> getTeams() {
		return teams;
	}
	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}
	public List<Game> getGames() {
		return games;
	}
	public void setGames(List<Game> games) {
		this.games = games;
	}
	public Community getCommunity() {
		return community;
	}
	public void setCommunity(Community community) {
		this.community = community;
	}
	@Override
	public String toString() {
		return "Season [id=" + id + ", name=" + name +  ", teams=" + teams + ", games="
				+ games + "]";
	}
	
	
}
