package org.lbcc.bms.bms_monolith.userservice.service.impl;

import org.lbcc.bms.bms_monolith.userservice.model.User;
import org.lbcc.bms.bms_monolith.userservice.repository.UserRepository;
import org.lbcc.bms.bms_monolith.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public boolean existsByUsername(String username) {
    return userRepository.existsByUsername(username);
  }

  @Override
  public boolean existsByEmail(String email) {
    return userRepository.existsByEmail(email);
  }

  @Override
  public void save(User user) {
    userRepository.save(user);
  }
}
