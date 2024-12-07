package org.lbcc.bms.bms_monolith.security.uesrnamepassword.repository;

import org.lbcc.bms.bms_monolith.common.entity.Role;
import org.lbcc.bms.bms_monolith.common.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByLabel(RoleType label);
}
