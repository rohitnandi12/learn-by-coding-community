package org.lbcc.bms.bms_monolith.admin.controller;

import jakarta.annotation.Nullable;
import jakarta.websocket.server.PathParam;
import org.lbcc.bms.bms_monolith.admin.dto.VendorOnboardResponse;
import org.lbcc.bms.bms_monolith.admin.constants.BMSConstants;
import org.lbcc.bms.bms_monolith.admin.dto.VendorSearchRequest;
import org.lbcc.bms.bms_monolith.common.entity.Vendor;
import org.lbcc.bms.bms_monolith.common.enums.VendorStatus;
import org.lbcc.bms.bms_monolith.admin.dto.VendorOnboardRequest;
import org.lbcc.bms.bms_monolith.admin.service.AdminService;
import org.lbcc.bms.bms_monolith.common.response.ApiListResponse;
import org.lbcc.bms.bms_monolith.common.response.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//TODO: all these endpoints will be allowed only by ADMIN

@RequestMapping("/vendors")
@RestController
public class AdminVendorOnboardingController {

    private final AdminService adminService;

    public AdminVendorOnboardingController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping()
    public ResponseEntity<ApiResponse<VendorOnboardResponse>> onboardNewVendor(@RequestBody VendorOnboardRequest vendorOnboardRequest) {
        VendorOnboardResponse response = adminService.onboardNewVendor(vendorOnboardRequest);
        ApiResponse<VendorOnboardResponse> apiResponse = ApiResponse.<VendorOnboardResponse>builder()
                .success(true)
                .message(BMSConstants.VENDOR_SUCCESS_MESSAGE)
                .data(response)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/{vendorId}/{status}")
    public ResponseEntity<ApiResponse<String>> updateVendorStatus(@PathVariable String vendorId, @PathVariable VendorStatus status) {
        Vendor vendor = adminService.updatedVendorStatus(vendorId, status);
        ApiResponse<String> response = ApiResponse.<String>builder()
                .success(true)
                .message(BMSConstants.VENDOR_UPDATE_SUCCESS_MESSAGE)
                .data(vendor.getId().toString())
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiListResponse<Vendor>> getAllVendors(
            @Nullable @ModelAttribute() VendorSearchRequest searchRequest,
            @PageableDefault(page = 0, size = 10)
            @SortDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {

        Page<Vendor> vendorsPage = adminService.searchVendors(searchRequest, pageable);
        ApiListResponse<Vendor> response = ApiListResponse.<Vendor>builder()
                .setPage(vendorsPage)
                .success(true)
                .message(BMSConstants.VENDOR_SUCCESS_MESSAGE)
                .build();
        return ResponseEntity.ok(response);
    }

}
