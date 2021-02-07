package com.project.dp.Sessions;

import com.project.dp.Entities.Users;

public class UserSession implements Session{

    private final Users user;
    public UserSession(Users user) {
        this.user = user;
    }

    @Override
    public void run() {
        System.out.println("User "+user.getLogin()+" Session");

    }
}
