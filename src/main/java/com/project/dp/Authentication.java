package com.project.dp;

import com.project.dp.Dao.UsersDao;
import com.project.dp.Entities.Users;
import com.project.dp.Services.UsersServiceImpl;
import com.project.dp.Sessions.AdminSession;
import com.project.dp.Sessions.Session;
import com.project.dp.Sessions.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Authentication {

    @Autowired
    private final UsersServiceImpl usersService;

    public Authentication(UsersServiceImpl usersService){
        this.usersService = usersService;
    }

    public Session login() {
        Scanner sc= new Scanner(System.in);
        System.out.println("login:");
        String login = sc.nextLine();
        Users user = usersService.findByLogin(login);
        System.out.println("passwd:");
        String passwd = sc.nextLine();
        if (user.getPassword().equals(passwd)){
            if (user.getRoleId() == 1){
                return new AdminSession(user);
            }
            else{
                return new UserSession(user);
            }
        }
        else{
            System.out.println("Błedne dane");
        }
        return null;
    }
}
