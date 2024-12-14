package org.lbcc.bms.bms_monolith.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.lbcc.bms.bms_monolith.common.enums.VendorStatus;

@Data
@AllArgsConstructor
public class VendorOnboardResponse {

    private String vendorId;
    private String vendorName;
    private VendorStatus vendorStatus;

}
