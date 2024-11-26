package org.lbcc.bms.bms_monolith.common.exception;

import org.lbcc.bms.bms_monolith.common.constants.BMSConstants;
import org.lbcc.bms.bms_monolith.common.response.ApiErrorResponse;
import org.lbcc.bms.bms_monolith.userservice.exeception.RoleNotFoundException;
import org.lbcc.bms.bms_monolith.userservice.exeception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGlobalException(Exception ex) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, BMSConstants.UNEXPECTED_ERROR_MESSAGE, BMSConstants.UNEXPECTED_ERROR_CODE);
    }

    public static ResponseEntity<ApiErrorResponse> buildErrorResponse(HttpStatus status, String message, String code) {
        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .message(message)
                .code(code)
                .build();
        return ResponseEntity.status(status).body(errorResponse);
    }



    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception) {

        List<String> errorMessage = new ArrayList<>();

        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errorMessage.add(error.getDefaultMessage());
        });
        return ResponseEntity
                .badRequest()
                .body(
                        ApiErrorResponse.builder()
                              //  .isSuccess(false)
                                .message("Registration Failed: Please provide valid data.")
                                .code(errorMessage.toString())
                                .build()
                );
    }

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> UserAlreadyExistsExceptionHandler(UserAlreadyExistsException exception) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(
                        ApiErrorResponse.builder()
                             //   .isSuccess(false)
                                .message(exception.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(value = RoleNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> RoleNotFoundExceptionHandler(RoleNotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(
                        ApiErrorResponse.builder()
                                .message(exception.getMessage())
                                .build()
                );
    }

}
