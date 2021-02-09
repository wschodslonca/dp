package com.project.dp.Sessions;

import com.project.dp.Entities.Users;
import com.project.dp.Filter.QueryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserSessionFactory implements SessionFactory{

    QueryType queryType;

    @Autowired
    public UserSessionFactory(QueryType queryType) {
        this.queryType = queryType;
    }

    public Session createSession(Users user) {
        Session us = new UserSession(queryType);
        us.setUser(user);
        return us;
    }
}
