package com.example.fintech.service;

import com.example.fintech.model.User;
import com.example.fintech.DTO.UserDTO;
import com.example.fintech.DTO.UserCreationDTO;
import com.example.fintech.DTO.UserUpdateDTO;
import com.example.fintech.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.List;

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
      .id(saved.getId())
      .firstName(saved.getFirstName())
      .lastName(saved.getLastName())
      .phoneNumber(saved.getPhoneNumber())
      .build();
  }

  public List<UserDTO> getAllUsers() {
      List<User> users = userRepository.findAll();

      return users.stream()
        .map(user -> UserDTO.builder()
          .id(user.getId())
          .firstName(user.getFirstName())
          .lastName(user.getLastName())
          .phoneNumber(user.getPhoneNumber())
          .build())
        .toList();
  }

  public UserDTO getUserById(UUID id) {
    User user = userRepository.findById(id).orElseThrow();

    return UserDTO.builder()
      .id(user.getId())
      .firstName(user.getFirstName())
      .lastName(user.getLastName())
      .phoneNumber(user.getPhoneNumber())
      .build();
  }

  public UserDTO updateUser(UUID id, UserUpdateDTO dto) {
    User user = userRepository.findById(id).orElseThrow();

    user.setFirstName(dto.getFirstName());
    user.setLastName(dto.getLastName());
    user.setPhoneNumber(dto.getPhoneNumber());

    User saved = userRepository.save(user);

    return UserDTO.builder()
      .id(saved.getId())
      .firstName(saved.getFirstName())
      .lastName(saved.getLastName())
      .phoneNumber(saved.getPhoneNumber())
      .build();
  }

  public void deleteUser(UUID id) {
    userRepository.deleteById(id);
  }
}