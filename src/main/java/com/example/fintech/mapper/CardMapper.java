package com.example.fintech.mapper;

import com.example.fintech.model.Card;
import com.example.fintech.DTO.CardDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CardMapper {

	@Mapping(source = "user.id", target = "userId")
	CardDTO toDto(Card card);

	@Mapping(target = "user", ignore = true)
	Card toEntity(CardDTO cardDto);
}