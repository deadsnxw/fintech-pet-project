package com.example.fintech.mapper;

import com.example.fintech.model.User;
import com.example.fintech.DTO.UserDTO;
import com.example.fintech.DTO.UserCreationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {
	
	UserDTO toDto(User user);

	@Mapping(target = "password", ignore = true)
	@Mapping(target = "role", ignore = true)
	@Mapping(target = "email", ignore = true)
	User toEntity(UserDTO userDto);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
	User toEntity(UserCreationDTO dto);
}