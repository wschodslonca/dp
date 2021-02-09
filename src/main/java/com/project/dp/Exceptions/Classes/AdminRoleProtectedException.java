package com.project.dp.Exceptions.Classes;

import com.project.dp.Exceptions.Messages.ExceptionMessages;

public class AdminRoleProtectedException extends RuntimeException {

    private static final long serialVersionUID = 2137420911691337L;

    public AdminRoleProtectedException() {
        super(ExceptionMessages.ADMIN_ROLE_PROTECTED.getErrorMessage());
    }
}
