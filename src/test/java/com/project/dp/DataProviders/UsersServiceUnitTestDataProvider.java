package com.project.dp.DataProviders;

import com.project.dp.Entities.Users;

import java.util.ArrayList;
import java.util.List;

public class UsersServiceUnitTestDataProvider {

    public static final Long userId = 1L;
    public static Users user;
    public static Users user1;
    public static Users user2;
    public static Users created_user;
    public static List<Users> users;
    static {
        user = Users.builder().userId(userId).login("test_login").password("test_password")
                .employeeId(1L).roleId(1L).build();
        user1 = Users.builder().userId(2L).login("test_login2").password("test_password2")
                .employeeId(2L).roleId(2L).build();
        user2 = Users.builder().userId(3L).login("test_login3").password("test_password3")
                .employeeId(3L).roleId(3L).build();
        created_user = Users.builder().userId(4L).login("new_login").password("new_password")
                .employeeId(4L).roleId(4L).build();
        users = new ArrayList<>();
        users.add(user);
        users.add(user1);
    }
}
