package com.thorstiland.spielplan.dto;

import lombok.Data;

@Data
public class GameDto {
	private long id;
	private Integer scoreHome;
	private Integer scoreAway;
	private String playDate;
	private TeamDto homeTeam;
	private TeamDto awayTeam;
}
