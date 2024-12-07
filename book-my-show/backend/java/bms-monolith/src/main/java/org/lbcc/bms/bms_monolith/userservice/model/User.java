package org.lbcc.bms.bms_monolith.userservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.lbcc.bms.bms_monolith.common.entity.BaseAuditingEntity;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseAuditingEntity {

    private String username;
    private String email;
    @Column(columnDefinition = "CHAR(10)")
    private String phone;
    private String password;
    @Builder.Default
    @Column(columnDefinition = "TINYINT(1)")
    private Boolean isActive = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    @Builder.Default
    private Set<Role> roles = new HashSet<>();
}
