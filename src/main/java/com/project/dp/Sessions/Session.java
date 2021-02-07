package com.project.dp.Sessions;

import com.project.dp.Entities.Users;
import com.project.dp.Management.StrategyContext;

public interface Session {
    void setUser(Users user);
    void run();
}
