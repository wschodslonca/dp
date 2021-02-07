package com.project.dp.Sessions;

import com.project.dp.Entities.Users;

public class AdminSession implements Session{

    private final Users user;
    public AdminSession(Users user) {
        this.user = user;
    }

    @Override
    public void run() {
        System.out.println("Admin "+user.getLogin()+" Session");
    }
}
