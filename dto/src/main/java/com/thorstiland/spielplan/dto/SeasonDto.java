package com.thorstiland.spielplan.dto;

import java.util.List;

import lombok.Data;

@Data
public class SeasonDto {
	private long id;
	private String name;
	private CommunityDto community;
	private List<TeamDto> teams;
	private List<GameDto> games;
}
