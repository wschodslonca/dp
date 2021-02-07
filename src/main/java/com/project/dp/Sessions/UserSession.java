package com.project.dp.Sessions;

import com.project.dp.Entities.Users;
import org.springframework.stereotype.Component;

@Component
public class UserSession implements Session{

    Users user;

    @Override
    public void setUser(Users user) {
        this.user = user;
    }

    @Override
    public void run() {
        System.out.println("User "+user.getLogin()+" Session");

    }
}
