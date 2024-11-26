package org.lbcc.bms.bms_monolith.userservice.exeception;

public class RoleNotFoundException extends Exception{
    public RoleNotFoundException(String message) {
        super(message);
    }
}