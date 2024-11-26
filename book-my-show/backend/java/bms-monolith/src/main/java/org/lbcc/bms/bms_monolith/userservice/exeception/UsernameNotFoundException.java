package org.lbcc.bms.bms_monolith.userservice.exeception;

public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException(String message) {
        super(message);
    }
}
