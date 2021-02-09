package com.project.dp;

import com.project.dp.Entities.Users;
import com.project.dp.Services.UsersService;
import com.project.dp.Sessions.Session;
import com.project.dp.Sessions.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Authentication {

    private static final int ADMIN_ROLE = 1;
    private final SessionFactory sessionFactory;
    private final UsersService usersService;


    @Autowired
    public Authentication(SessionFactory sessionFactory, UsersService usersService) {
        this.sessionFactory = sessionFactory;
        this.usersService = usersService;
    }

    public Session login() {
        Users user=null;
        boolean correctLogin = false;
        while(!correctLogin) {
            Scanner sc= new Scanner(System.in);
            System.out.println("login:");
            String login = sc.nextLine();
            if (login.equals("exit")) {
                System.exit(0);
            }
            //String login = "pietnastak";
            user = usersService.findByLogin(login);
            System.out.println("passwd:");
            String passwd = sc.nextLine();
            //String passwd = "15latzyje";
            if (user != null) {
                if (user.getPassword().equals(PasswordCrypter.md5(passwd))) {
                    correctLogin=true;
                } else {
                    System.out.println("Invalid login or password...");
                }
            }
            else {
                System.out.println("Invalid login or password...");
            }
        }
        if (user.getRoleId() == ADMIN_ROLE) {
            return sessionFactory.createAdminSession(user);
        } else {
            return sessionFactory.createUserSession(user);
        }
    }
}
