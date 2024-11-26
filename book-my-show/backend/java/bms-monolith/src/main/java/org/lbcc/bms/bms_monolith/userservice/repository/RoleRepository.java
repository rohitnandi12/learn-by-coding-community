package org.lbcc.bms.bms_monolith.userservice.repository;

import org.lbcc.bms.bms_monolith.userservice.common.RoleType;
import org.lbcc.bms.bms_monolith.userservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Role  findByName(RoleType name);
}
