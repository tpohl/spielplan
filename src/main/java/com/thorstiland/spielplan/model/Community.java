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

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.thorstiland.spielplan.model.jsonview.Views.Hierarchy;
@Entity
public class Community {
	@Id @GeneratedValue long id;
	
	private String name;
	
	@JsonView(Hierarchy.class)
	@ManyToMany(targetEntity = Team.class, fetch=FetchType.EAGER)
    @JoinTable(name="community2team")
	private List<Team> teams;

	@JsonView(Hierarchy.class)
	@JsonManagedReference("Community.seasons")
	@OneToMany(mappedBy = "community", cascade=CascadeType.REMOVE)
	private List<Season> seasons;
	
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

	public List<Season> getSeasons() {
		return seasons;
	}

	public void setSeasons(List<Season> seasons) {
		this.seasons = seasons;
	}

	@Override
	public String toString() {
		return "Community [id=" + id + ", name=" + name + ", teams=" + teams + ", seasons=" + seasons + "]";
	}
	
	
}
