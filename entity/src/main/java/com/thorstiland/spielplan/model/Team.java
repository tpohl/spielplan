package com.thorstiland.spielplan.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
@Entity
@Data
public class Team {
	@Id
	@GeneratedValue
	long id;

	private String name;

	private String playerName;

}
