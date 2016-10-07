package com.thorstiland.spielplan.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.thorstiland.spielplan.dto.SeasonDto;
import com.thorstiland.spielplan.model.Season;

@Mapper(uses = {TeamMapper.class, GameMapper.class})
public interface SeasonMapper {
	public SeasonDto toDto(Season season);

	List<SeasonDto> toDtos(List<Season> seasons);
}
