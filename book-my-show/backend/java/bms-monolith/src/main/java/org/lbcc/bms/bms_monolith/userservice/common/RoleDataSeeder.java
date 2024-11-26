package org.lbcc.bms.bms_monolith.userservice.common;

import jakarta.transaction.Transactional;
import org.lbcc.bms.bms_monolith.userservice.model.Role;
import org.lbcc.bms.bms_monolith.userservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
public class RoleDataSeeder {
  @Autowired private RoleRepository roleRepository;

  @EventListener
  @Transactional
  public void LoadRoles(ContextRefreshedEvent event) {

    List<RoleType> roles = Arrays.stream(RoleType.values()).toList();

    for (RoleType erole : roles) {
      if (roleRepository.findByName(erole) == null) {
        Role role = new Role();
        role.setName(erole);
        role.setDescription(erole.name());
        role.setCreatedBy("system");
        role.setCreatedDate(Instant.now());
        role.setLastModifiedDate(Instant.now());
        role.setLastModifiedBy("system");
        roleRepository.save(role);
      }
    }
  }
}
