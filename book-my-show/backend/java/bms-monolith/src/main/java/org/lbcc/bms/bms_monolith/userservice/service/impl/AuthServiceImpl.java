package org.lbcc.bms.bms_monolith.userservice.service.impl;

import org.lbcc.bms.bms_monolith.common.response.ApiResponse;
import org.lbcc.bms.bms_monolith.userservice.common.RoleFactory;
import org.lbcc.bms.bms_monolith.userservice.common.SignUpRequestDto;
import org.lbcc.bms.bms_monolith.userservice.exeception.RoleNotFoundException;
import org.lbcc.bms.bms_monolith.userservice.exeception.UserAlreadyExistsException;
import org.lbcc.bms.bms_monolith.userservice.model.Role;
import org.lbcc.bms.bms_monolith.userservice.model.User;
import org.lbcc.bms.bms_monolith.userservice.service.AuthService;
import org.lbcc.bms.bms_monolith.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserService userService;

   private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private RoleFactory roleFactory;

    @Override
    public ResponseEntity<ApiResponse> signUpUser(SignUpRequestDto signUpRequestDto)
            throws UserAlreadyExistsException , RoleNotFoundException {
        if (userService.existsByEmail(signUpRequestDto.getEmail())) {
            throw new UserAlreadyExistsException("Registration Failed: Provided email already exists. Try sign in or provide another email.");
        }
        if (userService.existsByUsername(signUpRequestDto.getUserName())) {
            throw new UserAlreadyExistsException("Registration Failed: Provided username already exists. Try sign in or provide another username.");
        }

        User user = createUser(signUpRequestDto);
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.builder()
                       // .isSuccess(true)
                        .message("User account has been successfully created!")
                        .build()
        );
    }

    private User createUser(SignUpRequestDto signUpRequestDto) throws RoleNotFoundException {
        return User.builder()
                .email(signUpRequestDto.getEmail())
                .username(signUpRequestDto.getUserName())
                .password(signUpRequestDto.getPassword())
                .enabled(true)
                .roles(determineRoles(signUpRequestDto.getRoles()))
                .build();
    }

    private Set<Role> determineRoles(Set<String> strRoles) throws RoleNotFoundException {
    Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            roles.add(roleFactory.getInstance("user"));
        } else {
            for (String role : strRoles) {
                roles.add(roleFactory.getInstance(role));
            }
        }
        return roles;
    }
}