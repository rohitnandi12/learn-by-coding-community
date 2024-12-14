package org.lbcc.bms.bms_monolith.admin.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.lbcc.bms.bms_monolith.admin.controller.AdminVendorOnboardingController;
import org.lbcc.bms.bms_monolith.admin.constants.BMSConstants;
import org.lbcc.bms.bms_monolith.admin.controller.VenueController;
import org.lbcc.bms.bms_monolith.common.exception.GlobalExceptionHandler;
import org.lbcc.bms.bms_monolith.common.response.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice(assignableTypes = {AdminVendorOnboardingController.class, VenueController.class})
public class AdminExceptionHandler {

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ResponseEntity<ApiErrorResponse> handleEventServiceException(Exception ex) {
        log.error("Exception occurred in vendor operation ", ex);
        return GlobalExceptionHandler.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), BMSConstants.VENDOR_SERVICE_ERROR);
    }

    @ExceptionHandler(VendorNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleVendorNotFoundException(VendorNotFoundException ex) {
        log.error("Vendor not found ", ex);
        return GlobalExceptionHandler.buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), BMSConstants.VENDOR_NOT_FOUND);
    }

    @ExceptionHandler(InvalidRequest.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidVendorRequest(InvalidRequest ex) {
        log.error("Invalid vendor request ", ex);
        return GlobalExceptionHandler.buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), BMSConstants.INVALID_REQUEST);
    }
}
