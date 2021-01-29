package com.project.dp.Exceptions.Messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum ExceptionMessages {

    NO_SUCH_USER("No such user"),
    NO_SUCH_ROLE("No such role"),
    USER_ALREADY_EXISTS("User already exists"),
    ROLE_ALREADY_EXISTS("Role already exists");
    @Getter
    @Setter
    private String errorMessage;
}
