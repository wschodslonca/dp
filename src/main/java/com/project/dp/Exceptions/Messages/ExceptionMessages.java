package com.project.dp.Exceptions.Messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public enum ExceptionMessages {

    NO_SUCH_USER("No such user"),
    NO_SUCH_ROLE("No such role"),
    NO_SUCH_SALARY("No such row in salary table"),
    NO_SUCH_EMPLOYEE("No such employee"),
    NO_SUCH_ACL("No such acl rule in ACL table"),
    USER_ALREADY_EXISTS("User already exists"),
    EMPLOYEE_ALREADY_EXISTS("Employee already exists"),
    ROLE_ALREADY_EXISTS("Role already exists");
    @Getter
    @Setter
    private String errorMessage;
}
