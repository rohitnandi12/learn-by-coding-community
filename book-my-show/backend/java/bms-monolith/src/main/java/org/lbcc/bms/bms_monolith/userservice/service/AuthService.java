package org.lbcc.bms.bms_monolith.userservice.service;

import org.lbcc.bms.bms_monolith.common.response.ApiResponse;
import org.lbcc.bms.bms_monolith.userservice.common.SignUpRequestDto;
import org.lbcc.bms.bms_monolith.userservice.exeception.RoleNotFoundException;
import org.lbcc.bms.bms_monolith.userservice.exeception.UserAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public interface AuthService {
    ResponseEntity<ApiResponse> signUpUser(SignUpRequestDto signUpRequestDto) throws UserAlreadyExistsException, RoleNotFoundException, org.lbcc.bms.bms_monolith.userservice.exeception.RoleNotFoundException;
}