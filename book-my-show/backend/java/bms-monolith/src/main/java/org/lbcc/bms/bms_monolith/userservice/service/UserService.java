package org.lbcc.bms.bms_monolith.userservice.service;

import org.lbcc.bms.bms_monolith.userservice.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    void save(User user);
}