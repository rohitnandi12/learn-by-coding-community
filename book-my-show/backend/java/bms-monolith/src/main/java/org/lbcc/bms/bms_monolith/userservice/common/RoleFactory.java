package org.lbcc.bms.bms_monolith.userservice.common;

import org.lbcc.bms.bms_monolith.userservice.exeception.RoleNotFoundException;
import org.lbcc.bms.bms_monolith.userservice.model.Role;
import org.lbcc.bms.bms_monolith.userservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleFactory {
    @Autowired
    RoleRepository roleRepository;

    public Role getInstance(String role) throws RoleNotFoundException {
        switch (role) {
            case "admin" -> {
                return roleRepository.findByName(RoleType.ADMIN);
            }
            case "user" -> {
                return roleRepository.findByName(RoleType.USER);
            }
            case "vendor" -> {
                return roleRepository.findByName(RoleType.VENDOR);
            }
            case "guest" -> {
                return roleRepository.findByName(RoleType.GUEST);
            }

            default -> throw  new RoleNotFoundException("No role found for " +  role);
        }
    }

}