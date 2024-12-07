package org.lbcc.bms.bms_monolith.security.uesrnamepassword.service;

import org.lbcc.bms.bms_monolith.common.entity.Role;
import org.lbcc.bms.bms_monolith.common.enums.RoleType;
import org.lbcc.bms.bms_monolith.security.uesrnamepassword.exeception.RoleNotFoundException;
import org.lbcc.bms.bms_monolith.security.uesrnamepassword.repository.RoleRepository;
import org.springframework.stereotype.Component;

@Component
public class RoleFactoryService {

    private final RoleRepository roleRepository;

    public RoleFactoryService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getInstance(String role) throws RoleNotFoundException {
        return switch (role.toLowerCase()) {
            case "admin" -> roleRepository.findByLabel(RoleType.ADMIN);
            case "user" -> roleRepository.findByLabel(RoleType.USER);
            case "vendor" -> roleRepository.findByLabel(RoleType.VENDOR);
            case "guest" -> roleRepository.findByLabel(RoleType.GUEST);
            default -> throw new RoleNotFoundException("No role found for " + role);
        };
    }
}