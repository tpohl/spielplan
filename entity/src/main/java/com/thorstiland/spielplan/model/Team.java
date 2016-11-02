package com.thorstiland.spielplan.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Team {
	@Id
	@GeneratedValue
	long id;

	private String name;
	
	@ManyToOne
	private User user;
}
