package com.example.fintech.service;

import org.springframework.transaction.annotation.Transactional;

import com.example.fintech.model.User;
import com.example.fintech.DTO.UserDTO;
import com.example.fintech.DTO.UserCreationDTO;
import com.example.fintech.DTO.UserUpdateDTO;
import com.example.fintech.repository.UserRepository;
import com.example.fintech.repository.CardRepository;
import com.example.fintech.mapper.UserMapper;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.List;

@Service
public class UserService {
  private final UserRepository userRepository;
  private final CardRepository cardRepository;

  private final UserMapper userMapper;

  public UserService(UserRepository userRepository, CardRepository cardRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
        this.userMapper = userMapper;
    }

  public UserDTO createUser(UserCreationDTO dto) {
    User user = userMapper.toEntity(dto);

    User saved = userRepository.save(user);

    return userMapper.toDto(saved);
  }

  public List<UserDTO> getAllUsers() {
      return userRepository.findAll().stream()
              .map(userMapper::toDto)
              .toList();
  }

  public UserDTO getUserById(UUID id) {
    return userRepository.findById(id)
        .map(userMapper::toDto)
        .orElseThrow(() -> new RuntimeException("User not found"));
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

    return userMapper.toDto(saved);
  }

  @Transactional
  public void deleteUser(UUID id) {
    cardRepository.deleteAllByUserId(id);
    userRepository.deleteById(id);
  }
}