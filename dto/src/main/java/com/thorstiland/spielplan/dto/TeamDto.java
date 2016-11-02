package com.thorstiland.spielplan.dto;

import lombok.Data;

@Data
public class TeamDto {
	private long id;
	private String name;
	private UserDto user;
}
