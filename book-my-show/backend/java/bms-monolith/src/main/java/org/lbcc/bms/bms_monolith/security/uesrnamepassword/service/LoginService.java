package org.lbcc.bms.bms_monolith.security.uesrnamepassword.service;

import org.lbcc.bms.bms_monolith.common.entity.RegisteredUser;
import org.lbcc.bms.bms_monolith.security.uesrnamepassword.dto.LogInRequest;
import org.lbcc.bms.bms_monolith.security.uesrnamepassword.dto.LogInResponse;
import org.lbcc.bms.bms_monolith.security.uesrnamepassword.model.UserDetailsModel;
import org.lbcc.bms.bms_monolith.security.uesrnamepassword.repository.RegisteredUserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RegisteredUserRepository registeredUserRepository;

    public LoginService(
            JwtService jwtService, AuthenticationManager authenticationManager,
            RegisteredUserRepository registeredUserRepository
    ) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.registeredUserRepository = registeredUserRepository;
    }


    public LogInResponse login(LogInRequest loginInRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginInRequest.username(),
                        loginInRequest.password()
                )
        );

        RegisteredUser authenticatedUser = registeredUserRepository
                .findByUsername(loginInRequest.username())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found!!"));
        String jwtToken = jwtService.generateToken(new UserDetailsModel(authenticatedUser));

        return new LogInResponse(
                jwtToken,
                jwtService.getDefaultExpirationTime()
        );
    }
}
