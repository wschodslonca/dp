package com.project.dp.Sessions;

import com.project.dp.Entities.Users;
import com.project.dp.Filter.QueryType;
import com.project.dp.Management.BaseManagement;
import com.project.dp.Management.RoleStrategy;
import com.project.dp.Management.StrategyContext;
import com.project.dp.Management.UserStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SessionFactory {

    QueryType queryType;
    BaseManagement baseManagement;
    StrategyContext strategyContext;
    RoleStrategy roleStrategy;
    UserStrategy userStrategy;

    @Autowired
    public SessionFactory(BaseManagement baseManagement, StrategyContext strategyContext, RoleStrategy roleStrategy, UserStrategy userStrategy, QueryType queryType) {
        this.baseManagement = baseManagement;
        this.strategyContext = strategyContext;
        this.roleStrategy = roleStrategy;
        this.userStrategy = userStrategy;
        this.queryType = queryType;
    }

    public UserSession createUserSession(Users user) {
        UserSession us = new UserSession(queryType);
        us.setUser(user);
        return us;
    }
    public AdminSession createAdminSession(Users user) {
        AdminSession as = new AdminSession(baseManagement,strategyContext,roleStrategy,userStrategy,queryType);
        as.setUser(user);
        return as;
    }

}
