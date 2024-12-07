package org.lbcc.bms.bms_monolith.security.uesrnamepassword.repository;

import org.lbcc.bms.bms_monolith.common.entity.RegisteredUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, String> {

    Optional<RegisteredUser> findByUsername(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
