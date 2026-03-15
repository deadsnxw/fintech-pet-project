package com.example.fintech.service;

import com.example.fintech.model.User;
import com.example.fintech.DTO.UserDTO;
import com.example.fintech.DTO.UserCreationDTO;
import com.example.fintech.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

  public UserDTO createUser(UserCreationDTO dto) {
    User user = User.builder()
        .firstName(dto.getFirstName())
        .lastName(dto.getLastName())
        .phoneNumber(dto.getPhoneNumber())
        .password(dto.getPassword())
        .build();

    User saved = userRepository.save(user);

    return UserDTO.builder()
      .firstName(saved.getFirstName())
      .lastName(saved.getLastName())
      .phoneNumber(saved.getPhoneNumber())
      .build();
  }
}