package org.lbcc.bms.bms_monolith.admin.dto;

import lombok.AllArgsConstructor;
import org.lbcc.bms.bms_monolith.common.dto.AddressDto;
import org.lbcc.bms.bms_monolith.common.entity.Vendor;

import java.util.List;

@AllArgsConstructor
public class VendorSearchResponse {
    private List<VendorDto> vendorDtoList;

    public static VendorDto buildVendorDtoFromVendor(Vendor vendor) {
        return VendorDto.builder()
                .id(vendor.getId()).name(vendor.getName())
                .contactNumber(vendor.getContactNumber()).email(vendor.getEmail())
                .address(AddressDto.addressToAddressDto(vendor.getAddress()))
                .website(vendor.getWebsite()).gstNo(vendor.getGstNo())
                .panNo(vendor.getPanNo()).status(vendor.getStatus())
                .registrationDate(String.valueOf(vendor.getRegistrationDate()))
                .logoUrl(vendor.getLogoUrl())
                .build();
    }

    public static List<VendorDto> buildVendorDtoListFromVendorList(List<Vendor> vendorList) {
        return vendorList.stream().map(VendorSearchResponse::buildVendorDtoFromVendor).toList();
    }
}
