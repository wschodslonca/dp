package com.project.dp.Exceptions.Classes;
import com.project.dp.Exceptions.Messages.ExceptionMessages;

public class ACLAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 2124974515222778752L;

    public ACLAlreadyExistsException() {
        super(ExceptionMessages.ACL_ALREADY_EXISTS.getErrorMessage());
    }
}

