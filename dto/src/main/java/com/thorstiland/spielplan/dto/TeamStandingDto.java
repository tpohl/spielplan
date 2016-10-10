package com.thorstiland.spielplan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamStandingDto {
	private TeamDto team;
	private int points;
	private int goals;
	private int caughtGoals;
	private int matchesPlayed;
	private int wins;
	private int draws;
	private int lost;
}
