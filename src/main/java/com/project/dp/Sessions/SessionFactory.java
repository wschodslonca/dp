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
public interface SessionFactory {
    Session createSession(Users user);
}
