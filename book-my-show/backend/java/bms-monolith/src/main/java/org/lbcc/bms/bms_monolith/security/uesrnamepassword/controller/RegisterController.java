package org.lbcc.bms.bms_monolith.security.uesrnamepassword.controller;

import jakarta.validation.Valid;
import org.lbcc.bms.bms_monolith.common.response.ApiResponse;
import org.lbcc.bms.bms_monolith.security.uesrnamepassword.dto.SignUpRequest;
import org.lbcc.bms.bms_monolith.security.uesrnamepassword.dto.SignUpResponse;
import org.lbcc.bms.bms_monolith.security.uesrnamepassword.service.RegisterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/register")
public class RegisterController {

    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<SignUpResponse>> registerUser(
            @RequestBody @Valid SignUpRequest signUpRequest
    ) {
        SignUpResponse signUpUser = registerService.signUpUser(signUpRequest);
        ApiResponse<SignUpResponse> response = ApiResponse.<SignUpResponse>builder()
                .success(true)
                .message("User registered successfully!!")
                .data(signUpUser)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}