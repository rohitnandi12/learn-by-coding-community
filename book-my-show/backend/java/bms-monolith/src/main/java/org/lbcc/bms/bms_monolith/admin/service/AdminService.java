package org.lbcc.bms.bms_monolith.admin.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.lbcc.bms.bms_monolith.admin.dto.*;
import org.lbcc.bms.bms_monolith.admin.exceptions.InvalidRequest;
import org.lbcc.bms.bms_monolith.common.entity.Vendor;
import org.lbcc.bms.bms_monolith.common.enums.VendorStatus;
import org.lbcc.bms.bms_monolith.common.response.ApiResponse;
import org.lbcc.bms.bms_monolith.admin.exceptions.VendorNotFoundException;
import org.lbcc.bms.bms_monolith.admin.repository.VendorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class AdminService {

    private final VendorRepository vendorRepository;

    public AdminService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    public VendorOnboardResponse onboardNewVendor(VendorOnboardRequest vendorOnboardRequest) {
        log.info("Onboarding new vendor {}", vendorOnboardRequest.getName());
        //TODO: will save the logoFile to file storage like s3 and get the URL and then update the vendor object before saving
        Vendor vendor = vendorRepository.save(VendorOnboardRequest.buildVendorFromDto(vendorOnboardRequest));
        log.info("Vendor onboarded successfully with id {}", vendor.getId());

        return new VendorOnboardResponse(vendor.getId().toString(), vendor.getName(), vendor.getStatus());
    }

    public List<VendorDto> searchVendor(String vendorName) {
        if (StringUtils.isEmpty(vendorName)) {
            throw new InvalidRequest("Vendor name cannot be empty");
        }

        log.info("Searching for vendor with name {}", vendorName);
        List<Vendor> vendorList = vendorRepository.findByNameContaining(vendorName);
        log.info("total vendors found {}", vendorList.size());
        return vendorList.isEmpty() ? List.of() :
                VendorSearchResponse.buildVendorDtoListFromVendorList(vendorList);


    }

    public Vendor findVendorById(UUID vendorId) {
        if (vendorId == null) {
            throw new InvalidRequest("Vendor id cannot be empty");
        }
        return vendorRepository.findById(vendorId).orElseThrow(() -> new VendorNotFoundException("Vendor not found"));
    }

    public Vendor updatedVendorStatus(String vendorId, VendorStatus vendorStatus) {
        validateVendorUpdateRequest(vendorId, vendorStatus);
        log.info("updating vendor with id {} to {} ", vendorId, vendorStatus);
        Vendor vendor = findVendorById(UUID.fromString(vendorId));
        vendor.setStatus(vendorStatus);
        vendorRepository.save(vendor);
        log.info("Vendor id {}  suspended successfully", vendorId);
        return vendor;
    }

    public Page<Vendor> searchVendors(VendorSearchRequest searchRequest, Pageable pageable) {
        try {
            Specification<Vendor> spec = VendorSpecifications.createSpecification(searchRequest);
            return vendorRepository.findAll(spec, pageable);
        } catch (Exception e) {
            log.error("Error in searching vendors", e);
            return Page.empty();
        }
    }

    private void validateVendorUpdateRequest(String vendorId, VendorStatus vendorStatus) {
        if (StringUtils.isEmpty(vendorId)) {
            throw new InvalidRequest("Vendor id cannot be empty");
        }
        if (vendorStatus == null || StringUtils.isEmpty(vendorStatus.name())) {
            throw new InvalidRequest("Vendor status cannot be empty");
        }
    }
}
