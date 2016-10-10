package com.thorstiland.spielplan.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.thorstiland.spielplan.dto.TeamDto;
import com.thorstiland.spielplan.model.Team;

@Mapper
public interface TeamMapper {
	public TeamDto toTeam(Team team);

	List<TeamDto> toTeamDtos(List<Team> teams);

	public Team toEntity(TeamDto team);
}
