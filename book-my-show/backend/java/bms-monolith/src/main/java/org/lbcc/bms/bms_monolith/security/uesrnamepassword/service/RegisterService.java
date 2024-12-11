package org.lbcc.bms.bms_monolith.security.uesrnamepassword.service;

import org.lbcc.bms.bms_monolith.common.entity.RegisteredUser;
import org.lbcc.bms.bms_monolith.common.entity.Role;
import org.lbcc.bms.bms_monolith.security.uesrnamepassword.dto.SignUpRequest;
import org.lbcc.bms.bms_monolith.security.uesrnamepassword.dto.SignUpResponse;
import org.lbcc.bms.bms_monolith.security.uesrnamepassword.exeception.RoleNotFoundException;
import org.lbcc.bms.bms_monolith.security.uesrnamepassword.exeception.UserAlreadyExistsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class RegisterService {

    private final RegisteredUserService registeredUserService;
    private final RoleFactoryService roleFactory;
    private final PasswordEncoder passwordEncoder;

    public RegisterService(
            RegisteredUserService registeredUserService, RoleFactoryService roleFactory,
            PasswordEncoder passwordEncoder
    ) {
        this.registeredUserService = registeredUserService;
        this.roleFactory = roleFactory;
        this.passwordEncoder = passwordEncoder;
    }

    public SignUpResponse signUpUser(SignUpRequest signUpRequest)
            throws UserAlreadyExistsException, RoleNotFoundException {
        if (registeredUserService.existsByEmail(signUpRequest.getEmail())) {
            throw new UserAlreadyExistsException("Registration Failed: Provided email already exists. Try sign in or provide another email.");
        }
        if (registeredUserService.existsByUsername(signUpRequest.getUsername())) {
            throw new UserAlreadyExistsException("Registration Failed: Provided username already exists. Try sign in or provide another username.");
        }

        RegisteredUser registeredUser = createUser(signUpRequest);
        registeredUserService.save(registeredUser);
        return new SignUpResponse(registeredUser.getUsername());
    }

    private RegisteredUser createUser(SignUpRequest signUpRequest) throws RoleNotFoundException {
        return RegisteredUser.builder()
                .email(signUpRequest.getEmail())
                .username(signUpRequest.getUsername())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .roles(determineRoles(signUpRequest.getRoles()))
                .build();
    }

    private Set<Role> determineRoles(Set<String> strRoles) throws RoleNotFoundException {
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            roles.add(roleFactory.getInstance("user"));
        } else {
            for (String role : strRoles) {
                roles.add(roleFactory.getInstance(role.toLowerCase()));
            }
        }
        return roles;
    }
}