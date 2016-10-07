package com.thorstiland.spielplan.dto;

import java.util.List;

import lombok.Data;

@Data
public class CommunityDto {
	private long id;
	private String name;
	private List<TeamDto> teams;
	private List<SeasonDto> seasons;
}
