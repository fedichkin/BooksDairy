package com.bookDairy.domain.enums;

/**
 * List of roles
 *
 * @KontarMaryna
 */

public enum Role {

    ADMIN, USER;

    public String getLabel(){
        return name().substring(0,1) + name().substring(1).toLowerCase();
    }
}
