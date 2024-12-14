package org.lbcc.bms.bms_monolith.admin.dto;

import lombok.Builder;
import lombok.Data;
import org.lbcc.bms.bms_monolith.common.dto.AddressDto;
import org.lbcc.bms.bms_monolith.common.enums.VendorStatus;

import java.util.UUID;

@Data
@Builder
public class VendorDto {

    private UUID id;
    private String name;
    private String contactNumber;
    private String email;
    private AddressDto address;
    private String website;
    private String gstNo;
    private String panNo;
    private VendorStatus status;
    private String registrationDate;
    private String logoUrl;

}
