package org.lbcc.bms.bms_monolith.security.uesrnamepassword.service;

import org.lbcc.bms.bms_monolith.common.entity.RegisteredUser;
import org.lbcc.bms.bms_monolith.security.uesrnamepassword.model.UserDetailsModel;
import org.lbcc.bms.bms_monolith.security.uesrnamepassword.repository.RegisteredUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DaoUserDetailsService implements UserDetailsService {

    private final RegisteredUserRepository registeredUserRepository;

    public DaoUserDetailsService(RegisteredUserRepository registeredUserRepository) {
        this.registeredUserRepository = registeredUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        RegisteredUser registeredUser =
                registeredUserRepository
                        .findByUsername(username)
                        .orElseThrow(
                                () -> new UsernameNotFoundException("User Not Found with username: " + username));

        return new UserDetailsModel(registeredUser);
    }
}
