package com.example.fintech.service;

import org.springframework.transaction.annotation.Transactional;

import com.example.fintech.model.User;
import com.example.fintech.DTO.UserDTO;
import com.example.fintech.DTO.UserCreationDTO;
import com.example.fintech.DTO.UserUpdateDTO;
import com.example.fintech.repository.UserRepository;
import com.example.fintech.repository.CardRepository;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.List;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final CardRepository cardRepository;

  public UserService(UserRepository userRepository, CardRepository cardRepository) {
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
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

    if(dto.getFirstName() != null) {
      user.setFirstName(dto.getFirstName());
    }

    if(dto.getLastName() != null) {
      user.setLastName(dto.getLastName());
    }

    if(dto.getPhoneNumber() != null) {
      user.setPhoneNumber(dto.getPhoneNumber());
    }

    User saved = userRepository.save(user);

    return UserDTO.builder()
      .id(saved.getId())
      .firstName(saved.getFirstName())
      .lastName(saved.getLastName())
      .phoneNumber(saved.getPhoneNumber())
      .build();
  }

  @Transactional
  public void deleteUser(UUID id) {
    cardRepository.deleteAllByUserId(id);
    userRepository.deleteById(id);
  }
}