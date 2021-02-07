package com.project.dp.Exceptions.Classes;
import com.project.dp.Exceptions.Messages.ExceptionMessages;

public class InvalidCommandException extends RuntimeException{
    private static final long serialVersionUID = 2645315523262426567L;

    public InvalidCommandException() {
        super(ExceptionMessages.INVALID_COMMAND.getErrorMessage());
    }
}
