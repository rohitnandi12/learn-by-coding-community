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
import java.util.UUID;

@Component
public class RoleDataSeeder {
    private final RoleRepository roleRepository;

    public RoleDataSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @EventListener
    @Transactional
    public void loadRoles(ContextRefreshedEvent event) {

        List<RoleType> roles = Arrays.stream(RoleType.values()).toList();

        for (RoleType roleType : roles) {
            if (roleRepository.findByLabel(roleType) == null) {
                Role role = new Role();
                role.setId(UUID.randomUUID().toString());
                role.setLabel(roleType);
                role.setDescription(roleType.name());
                role.setCreatedBy("system");
                role.setCreatedDate(Instant.now());
                roleRepository.save(role);
            }
        }
    }
}
