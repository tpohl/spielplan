package com.thorstiland.spielplan.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.thorstiland.spielplan.dto.CommunityDto;
import com.thorstiland.spielplan.model.Community;
import com.thorstiland.spielplan.dto.CommunityBasicDto;

@Mapper(uses = {TeamMapper.class, SeasonMapper.class})
public interface CommunityMapper {
	public CommunityDto toDto(Community entity);
	
	public Community toEntity(CommunityBasicDto dto);

	List<CommunityDto> toDtos(List<Community> entities);
}
