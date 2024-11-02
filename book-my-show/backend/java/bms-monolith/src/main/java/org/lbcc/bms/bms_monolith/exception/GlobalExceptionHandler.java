package org.lbcc.bms.bms_monolith.exception;

import org.lbcc.bms.bms_monolith.common.constants.BMSConstants;
import org.lbcc.bms.bms_monolith.common.response.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EventServiceException.class)
    public ResponseEntity<ApiErrorResponse> handleEventServiceException(EventServiceException ex) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), BMSConstants.EVENT_SERVICE_ERROR);
    }

    @ExceptionHandler(InvalidPaginationParameterException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidPaginationParameter(InvalidPaginationParameterException ex) {
        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .success(false)
                .message(ex.getMessage())
                .code(BMSConstants.INVALID_PAGINATION_PARAMETER)
                .timestamp(Instant.now())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGlobalException(Exception ex) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, BMSConstants.UNEXPECTED_ERROR_MESSAGE, BMSConstants.UNEXPECTED_ERROR_CODE);
    }

    private ResponseEntity<ApiErrorResponse> buildErrorResponse(HttpStatus status, String message, String code) {
        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .message(message)
                .code(code)
                .build();
        return ResponseEntity.status(status).body(errorResponse);
    }
}
