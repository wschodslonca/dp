package com.project.dp.Sessions;

import com.project.dp.Entities.Users;
import com.project.dp.Exceptions.Classes.InvalidCommandException;
import com.project.dp.Management.BaseManagement;
import com.project.dp.Management.RoleStrategy;
import com.project.dp.Management.StrategyContext;
import com.project.dp.Management.UserStrategy;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AdminSession implements Session{

    Users user;
    private final BaseManagement baseManagement;
    StrategyContext strategyContext;
    RoleStrategy roleStrategy;
    UserStrategy userStrategy;

    @Autowired
    public AdminSession(BaseManagement baseManagement, StrategyContext strategyContext, RoleStrategy roleStrategy, UserStrategy userStrategy) {
        this.baseManagement = baseManagement;
        this.strategyContext = strategyContext;
        this.roleStrategy = roleStrategy;
        this.userStrategy = userStrategy;
    }

    @Override
    public void setUser(Users user) {
        this.user = user;
    }

    private void executeCommand(String command) throws InvalidCommandException {
        try {
            String[] c = command.split(" ");
            int len = c.length;
            if (len == 1 && c[0].equals("query")) {
                System.out.println("query");
            }
            else if (len ==1 && c[0].equals("tree")) {
                this.baseManagement.printTree();
            }
            else if (len == 5 && c[0].equals("gainacc") && c[1].equals("-R")) {
                strategyContext.setStrategy(roleStrategy);
                strategyContext.gainAccess(Long.parseLong(c[2]),c[3],Long.parseLong(c[4]));
            } else if (len == 5 && c[0].equals("gainacc") && c[1].equals("-U")) {
                strategyContext.setStrategy(userStrategy);
                strategyContext.gainAccess(Long.parseLong(c[2]),c[3],Long.parseLong(c[4]));
            } else if (len == 5 && c[0].equals("rmacc") && c[1].equals("-R")) {
                strategyContext.setStrategy(roleStrategy);
                strategyContext.revokeAccess(Long.parseLong(c[2]),c[3],Long.parseLong(c[4]));
            } else if (len == 5 && c[0].equals("rmacc") && c[1].equals("-U")) {
                strategyContext.setStrategy(userStrategy);
                strategyContext.revokeAccess(Long.parseLong(c[2]),c[3],Long.parseLong(c[4]));
            } else if (len == 6 && c[0].equals("gainacc") && c[1].equals("-R") && c[2].equals("-s")) {
                strategyContext.setStrategy(roleStrategy);
                strategyContext.gainOneAccess(Long.parseLong(c[3]),c[4],Long.parseLong(c[5]));
            } else if (len == 6 && c[0].equals("gainacc") && c[1].equals("-U") && c[2].equals("-s")) {
                strategyContext.setStrategy(userStrategy);
                strategyContext.gainOneAccess(Long.parseLong(c[3]),c[4],Long.parseLong(c[5]));
            }
            else if (len == 6 && c[0].equals("rmacc") && c[1].equals("-R") && c[2].equals("-s")) {
                strategyContext.setStrategy(roleStrategy);
                strategyContext.revokeOneAccess(Long.parseLong(c[3]),c[4],Long.parseLong(c[5]));
            }
            else if (len == 6 && c[0].equals("rmacc") && c[1].equals("-U") && c[2].equals("-s")) {
                strategyContext.setStrategy(userStrategy);
                strategyContext.revokeOneAccess(Long.parseLong(c[3]),c[4],Long.parseLong(c[5]));
            }
            else {
                throw new InvalidCommandException();
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            throw new InvalidCommandException();
        }
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Admin "+user.getLogin()+" Session");
        while(true) {
            String command = sc.nextLine();
            try {
                executeCommand(command);
            }
            catch(InvalidCommandException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
