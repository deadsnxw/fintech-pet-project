package com.example.fintech.mapper;

import com.example.fintech.model.User;
import com.example.fintech.DTO.UserDTO;
import com.example.fintech.DTO.UserCreationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	UserDTO toDto(User user);

	@Mapping(target = "password", ignore = true)
	User toEntity(UserDTO userDto);

	@Mapping(target = "id", ignore = true)
	User toEntity(UserCreationDTO dto);
}