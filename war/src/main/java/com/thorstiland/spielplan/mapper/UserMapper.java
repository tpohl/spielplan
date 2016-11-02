package com.thorstiland.spielplan.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.thorstiland.spielplan.dto.UserDto;
import com.thorstiland.spielplan.model.User;

@Mapper
public interface UserMapper {
	public UserDto toDto(User user);

	List<UserDto> toDtos(List<User> users);

	public User toEntity(UserDto team);
}
