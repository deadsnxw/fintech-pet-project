package com.example.fintech.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.fintech.model.User;
import com.example.fintech.DTO.UserDTO;
import com.example.fintech.DTO.UserCreationDTO;
import com.example.fintech.mapper.UserMapper;
import com.example.fintech.repository.UserRepository;
import com.example.fintech.repository.CardRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	@Mock
	private UserRepository userRepository;

	@Mock
	private CardRepository cardRepository;

	@Mock
	private UserMapper userMapper;

	@InjectMocks
	private UserService userService;

	@Test
	public void shouldCreateUser() {
		UserDTO expectedDto = UserDTO.builder()
					.firstName("Max")
					.lastName("Verstappen")
					.phoneNumber("123456789")
					.build();

		UserCreationDTO dto = new UserCreationDTO("Max", "Verstappen", "123456789", "123456");
		
		User user = User.builder()
		    .firstName("Max")
		    .lastName("Verstappen")
		    .phoneNumber("123456789")
		    .build();

		when(userMapper.toEntity(any(UserCreationDTO.class))).thenReturn(user);

		when(userMapper.toDto(any())).thenReturn(expectedDto);

		when(userRepository.save(any())).thenReturn(user);

		UserDTO result = userService.createUser(dto);

		assertEquals("Max", result.getFirstName());
	}
}