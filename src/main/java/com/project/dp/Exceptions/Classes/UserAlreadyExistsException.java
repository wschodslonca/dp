package com.project.dp.Exceptions.Classes;
import com.project.dp.Exceptions.Messages.ExceptionMessages;
public class UserAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 2124974515222778752L;

    public UserAlreadyExistsException() {
        super(ExceptionMessages.USER_ALREADY_EXISTS.getErrorMessage());
    }
}
