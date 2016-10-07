package com.thorstiland.spielplan.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.thorstiland.spielplan.dto.GameDto;
import com.thorstiland.spielplan.model.Game;

@Mapper(uses = TeamMapper.class)
public interface GameMapper {
	public GameDto toGameDto(Game game);

	List<GameDto> toGameDtos(List<Game> games);
}
