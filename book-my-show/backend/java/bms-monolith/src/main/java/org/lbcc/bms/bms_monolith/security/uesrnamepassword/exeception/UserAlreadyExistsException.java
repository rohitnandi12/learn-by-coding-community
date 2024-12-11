package org.lbcc.bms.bms_monolith.security.uesrnamepassword.exeception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
