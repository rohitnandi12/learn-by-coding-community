package org.lbcc.bms.bms_monolith.userservice.controller;

import org.lbcc.bms.bms_monolith.common.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/")
    public ResponseEntity<ApiResponse<?>> Test() {
    return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().success(true).message(" jwt token test").build());
    }
}
