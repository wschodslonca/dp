package com.project.dp.Exceptions.Classes;
import com.project.dp.Exceptions.Messages.ExceptionMessages;

public class NoSuchSalaryException extends RuntimeException{
    private static final long serialVersionUID = 2645315523262426567L;

    public NoSuchSalaryException() {
        super(ExceptionMessages.NO_SUCH_SALARY.getErrorMessage());
    }
}
