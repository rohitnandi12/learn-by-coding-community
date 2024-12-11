package org.lbcc.bms.bms_monolith.security.uesrnamepassword.service;

import org.lbcc.bms.bms_monolith.common.entity.RegisteredUser;
import org.lbcc.bms.bms_monolith.security.uesrnamepassword.repository.RegisteredUserRepository;
import org.springframework.stereotype.Service;

@Service
public class RegisteredUserService {

    private final RegisteredUserRepository registeredUserRepository;

    public RegisteredUserService(RegisteredUserRepository registeredUserRepository) {
        this.registeredUserRepository = registeredUserRepository;
    }

    public boolean existsByUsername(String username) {
        return registeredUserRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return registeredUserRepository.existsByEmail(email);
    }

    public void save(RegisteredUser registeredUser) {
        registeredUserRepository.save(registeredUser);
    }
}
