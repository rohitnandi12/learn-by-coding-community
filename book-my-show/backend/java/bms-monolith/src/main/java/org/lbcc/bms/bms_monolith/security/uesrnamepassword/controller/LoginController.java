package org.lbcc.bms.bms_monolith.security.uesrnamepassword.controller;

import jakarta.validation.Valid;
import org.lbcc.bms.bms_monolith.common.response.ApiResponse;
import org.lbcc.bms.bms_monolith.security.uesrnamepassword.dto.LogInRequest;
import org.lbcc.bms.bms_monolith.security.uesrnamepassword.dto.LogInResponse;
import org.lbcc.bms.bms_monolith.security.uesrnamepassword.service.LoginService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/login")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<LogInResponse>> login(
            @RequestBody @Valid LogInRequest logInRequest
    ) {
        LogInResponse loginResponse = loginService.login(logInRequest);
        ApiResponse<LogInResponse> response = ApiResponse.<LogInResponse>builder()
                .success(true)
                .message("Login successful!!")
                .data(loginResponse)
                .build();
        return ResponseEntity.ok(response);
    }
}
