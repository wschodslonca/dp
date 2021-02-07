package com.project.dp.Sessions;

import com.project.dp.Entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionFactory {
    AdminSession adminSession;
    UserSession userSession;

    @Autowired
    public SessionFactory(AdminSession adminSession, UserSession userSession) {
        this.adminSession = adminSession;
        this.userSession = userSession;
    }
    public UserSession getUserSession(Users user) {
        userSession.setUser(user);
        return userSession;
    }
    public AdminSession getAdminSession(Users user) {
        adminSession.setUser(user);
        return adminSession;
    }

}
