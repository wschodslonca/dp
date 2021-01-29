package com.project.dp.Exceptions.Classes;
import com.project.dp.Exceptions.Messages.ExceptionMessages;

public class RoleAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 2124974515222778752L;

    public RoleAlreadyExistsException() {
        super(ExceptionMessages.ROLE_ALREADY_EXISTS.getErrorMessage());
    }
}
