package com.thorstiland.spielplan.mapper;

import java.util.Collection;

import org.mapstruct.Mapper;

import com.thorstiland.spielplan.dto.SeasonDto;
import com.thorstiland.spielplan.model.Season;

@Mapper(uses = {TeamMapper.class, MatchMapper.class})
public interface SeasonMapper {
	public SeasonDto toDto(Season season);

	Collection<SeasonDto> toDtos(Collection<Season> seasons);
}
