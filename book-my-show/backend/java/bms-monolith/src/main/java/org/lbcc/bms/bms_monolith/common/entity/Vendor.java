package org.lbcc.bms.bms_monolith.common.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lbcc.bms.bms_monolith.common.enums.VendorStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "vendors")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vendor extends BaseAuditingEntity {

    @Column(nullable = false, length = 20)
    private String name;
    private String contactNumber;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false)
    private String address;

    @Column(nullable = true, length = 100)
    private String website;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private VendorStatus status;

    @Column(nullable = false)
    private LocalDateTime registrationDate;

    @Column(nullable = false, length = 255)
    private String logoUrl;
}
