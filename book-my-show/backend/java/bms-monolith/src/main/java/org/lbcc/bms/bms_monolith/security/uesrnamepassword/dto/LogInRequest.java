package org.lbcc.bms.bms_monolith.security.uesrnamepassword.dto;

public record LogInRequest(
        //TODO: Add validation
        String username,
        String password
) {
}
