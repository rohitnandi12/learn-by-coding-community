package org.lbcc.bms.bms_monolith.security.uesrnamepassword.exeception;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(String message) {
        super(message);
    }
}