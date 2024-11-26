package org.lbcc.bms.bms_monolith.userservice.exeception;

public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
