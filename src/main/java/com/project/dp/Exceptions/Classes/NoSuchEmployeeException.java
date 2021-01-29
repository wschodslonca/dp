package com.project.dp.Exceptions.Classes;
import com.project.dp.Exceptions.Messages.ExceptionMessages;

public class NoSuchEmployeeException extends RuntimeException{
    private static final long serialVersionUID = 2645315523262426567L;

    public NoSuchEmployeeException() {
        super(ExceptionMessages.NO_SUCH_EMPLOYEE.getErrorMessage());
    }
}
