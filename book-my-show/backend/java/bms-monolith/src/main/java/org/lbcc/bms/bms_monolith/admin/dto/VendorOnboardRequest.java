package org.lbcc.bms.bms_monolith.admin.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.lbcc.bms.bms_monolith.common.dto.AddressDto;
import org.lbcc.bms.bms_monolith.common.entity.Vendor;
import org.lbcc.bms.bms_monolith.common.enums.VendorStatus;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@Builder
public class VendorOnboardRequest {

    @NotBlank(message = "Vendor name is required")
    @Size(min = 3, max = 30, message = "Vendor name must be at most 30 characters")
    private String name;

    @NotBlank(message = "Contact is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid contact number. Please provide a 10 digit number.")
    private String contactNumber;

    @NotBlank(message = "Email is required")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", message = "Invalid email format. Please provide a valid email.")
    private String email;

    @NotBlank(message = "Address is required")
    private AddressDto address;

    @NotBlank(message = "Website is required")
    private String website;

    @NotBlank(message = "GST number is required")
    @Pattern(regexp = "^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[0-9]{1}[Z]{1}[0-9]{1}$", message = "Invalid GST number. Please provide a valid GST number.")
    private String gstNo;

    @NotBlank(message = "PAN number is required")
    @Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$", message = "Invalid PAN number. Please provide a valid PAN number.")
    private String panNo;

    @NotBlank(message = "Registration date is required")
    @Pattern(regexp = "^[0-9]{2}-[0-9]{2}-[0-9]{4}$", message = "Invalid date format. Please use dd-MM-yyyy.")
    private String registrationDate;

//    @NotNull(message = "Logo is required")
    private MultipartFile logoFile;

    public static Vendor buildVendorFromDto(VendorOnboardRequest vendorOnboardRequest) {
        return Vendor.builder()
                .name(vendorOnboardRequest.getName())
                .contactNumber(vendorOnboardRequest.getContactNumber())
                .email(vendorOnboardRequest.getEmail())
                .address(AddressDto.addressDtoToAddress(vendorOnboardRequest.getAddress()))
                .website(vendorOnboardRequest.getWebsite())
                .gstNo(vendorOnboardRequest.getGstNo())
                .panNo(vendorOnboardRequest.getPanNo())
                .registrationDate(LocalDateTime.parse(vendorOnboardRequest.getRegistrationDate()))
                .logoUrl("logo-url-logo-url-logo-url-logo-url")
                .status(VendorStatus.ACTIVE)
                .build();
    }
}