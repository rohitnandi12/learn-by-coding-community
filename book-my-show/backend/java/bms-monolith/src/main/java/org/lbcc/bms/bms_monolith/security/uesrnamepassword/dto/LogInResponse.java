package org.lbcc.bms.bms_monolith.security.uesrnamepassword.dto;

public record LogInResponse(
        String token,
        long expiresIn
) {
}
