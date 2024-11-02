package org.lbcc.bms.bms_monolith.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lbcc.bms.bms_monolith.common.entity.BaseAuditingEntity;
import org.lbcc.bms.bms_monolith.entity.enums.VendorStatus;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vendor extends BaseAuditingEntity {

    private String name;
    private String contactNumber;
    private String email;
    private String address;
    private String website;

    @Enumerated(EnumType.STRING)
    private VendorStatus status;

    @Column(nullable = true, length = 100)
    @Size(min = 10, max = 100, message = "Registration date must be between 10 and 100 characters.")
    private String registrationDate;

    @Column(nullable = false, length = 255)
    @Size(min = 25, max = 255, message = "Logo URL must be between 25 and 255 characters.")
    private String logoUrl;
}
