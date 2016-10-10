package com.thorstiland.spielplan.dto;

import java.util.List;

import lombok.Data;

@Data
public class SeasonDto {
	private long id;
	private String name;
	private List<TeamDto> teams;
	private List<MatchDto> matches;
}
