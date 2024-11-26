package org.lbcc.bms.bms_monolith.userservice.controller;

import jakarta.validation.Valid;
import org.lbcc.bms.bms_monolith.common.response.ApiResponse;
import org.lbcc.bms.bms_monolith.userservice.common.SignUpRequestDto;
import org.lbcc.bms.bms_monolith.userservice.exeception.RoleNotFoundException;
import org.lbcc.bms.bms_monolith.userservice.exeception.UserAlreadyExistsException;
import org.lbcc.bms.bms_monolith.userservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody @Valid SignUpRequestDto signUpRequestDto)
            throws UserAlreadyExistsException, RoleNotFoundException {
        return authService.signUpUser(signUpRequestDto);
    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> signIn(@RequestBody @Valid SignUpRequestDto signUpRequestDto)
            throws UserAlreadyExistsException, RoleNotFoundException {
        return authService.signUpUser(signUpRequestDto);
    }

}