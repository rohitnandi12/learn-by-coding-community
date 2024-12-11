package org.lbcc.bms.bms_monolith.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@SuperBuilder
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Table(name = "registered_users")
public class RegisteredUser extends BaseAuditingEntity {

    @Column(length = 20)
    private String username;

    @Column(length = 25)
    private String email;

    @Column(columnDefinition = "CHAR(10)")
    private String phone;

    @Column(length = 20)
    @JsonIgnore
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
