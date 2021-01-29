package com.project.dp.Exceptions.Classes;
import com.project.dp.Exceptions.Messages.ExceptionMessages;

public class EmployeeAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 2124974515222778752L;

    public EmployeeAlreadyExistsException() {
        super(ExceptionMessages.EMPLOYEE_ALREADY_EXISTS.getErrorMessage());
    }
}
