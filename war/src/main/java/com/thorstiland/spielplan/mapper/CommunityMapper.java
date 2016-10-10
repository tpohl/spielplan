package com.thorstiland.spielplan.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.thorstiland.spielplan.dto.CommunityDto;
import com.thorstiland.spielplan.model.Community;

@Mapper(uses = {TeamMapper.class, SeasonMapper.class})
public interface CommunityMapper {
	public CommunityDto toDto(Community entity);
	
	public Community toEntity(CommunityDto dto);

	List<CommunityDto> toDtos(List<Community> entities);
}
