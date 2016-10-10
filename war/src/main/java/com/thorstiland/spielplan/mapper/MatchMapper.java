package com.thorstiland.spielplan.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.thorstiland.spielplan.dto.MatchDto;
import com.thorstiland.spielplan.model.Match;

@Mapper(uses = TeamMapper.class)
public interface MatchMapper {
	public MatchDto toDto(Match match);

	List<MatchDto> toDtos(List<Match> matches);
}
