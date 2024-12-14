package org.lbcc.bms.bms_monolith.admin.dto;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class VendorSearchRequest {

    private String vendorName;
    private String vendorStatus;
    private String registrationDate;

    public boolean hasSearchCriteria() {
        return !StringUtils.isBlank(vendorName) ||
                !StringUtils.isBlank(vendorStatus) ||
                !StringUtils.isBlank(registrationDate);
    }
}
