package com.project.dp.Sessions;

import com.project.dp.Entities.Users;

public interface Session {
    void setUser(Users user);
    void run();
}
