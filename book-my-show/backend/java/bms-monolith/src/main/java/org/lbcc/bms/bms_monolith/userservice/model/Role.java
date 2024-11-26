package org.lbcc.bms.bms_monolith.userservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.lbcc.bms.bms_monolith.userservice.common.RoleType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "role_id")
  private Long roleId;
  @Enumerated(EnumType.STRING)
  private RoleType name;

  @Column private String description;
}
