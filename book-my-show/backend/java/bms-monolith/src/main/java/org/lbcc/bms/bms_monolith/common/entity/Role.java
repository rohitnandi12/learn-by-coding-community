package org.lbcc.bms.bms_monolith.common.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.lbcc.bms.bms_monolith.common.enums.RoleType;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role extends BaseAuditingEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType label;

    @Column(nullable = false, length = 50)
    private String description;
}
