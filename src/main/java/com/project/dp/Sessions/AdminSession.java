package com.project.dp.Sessions;

import com.project.dp.Entities.Users;
import com.project.dp.Exceptions.Classes.InvalidCommandException;
import com.project.dp.Filter.QueryType;
import com.project.dp.Management.BaseManagement;
import com.project.dp.Management.RoleStrategy;
import com.project.dp.Management.StrategyContext;
import com.project.dp.Management.UserStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Scanner;
import java.util.List;

@Component
public class AdminSession implements Session{

    Users user;
    private final QueryType queryType;
    private final BaseManagement baseManagement;
    private final StrategyContext strategyContext;
    private final RoleStrategy roleStrategy;
    private final UserStrategy userStrategy;

    @Autowired
    public AdminSession(BaseManagement baseManagement, StrategyContext strategyContext, RoleStrategy roleStrategy, UserStrategy userStrategy, QueryType queryType) {
        this.baseManagement = baseManagement;
        this.strategyContext = strategyContext;
        this.roleStrategy = roleStrategy;
        this.userStrategy = userStrategy;
        this.queryType = queryType;
    }

    @Override
    public void setUser(Users user) {
        this.user = user;
    }

    private void executeCommand(String command) throws InvalidCommandException {
        try {
            String[] c = command.split(" ");
            int len = c.length;

            //query
            if (len == 1 && c[0].equals("q")) {
                Scanner sc = new Scanner(System.in);
                List<Object[]> res = queryType.query(sc.nextLine(),user.getUserId());
                if (!res.isEmpty()) {
                    if (res.get(0) instanceof Object[]) {
                        int reslen = 0;
                        reslen = res.get(0).length;
                        for (Object[] o : res) {
                            for (int i = 0; i < reslen; i++) {
                                System.out.print(o[i] + " ");
                            }
                            System.out.println();
                        }
                    }
                    else {
                        for (Object o : res) {
                            System.out.println(o);
                        }
                    }
                }
            }
            //man
            else if (len==1 && c[0].equals("man")) {
                System.out.println("tree - prints tree of roles and users");
                System.out.println("q - insert query mode");
                System.out.println("gainacc [-R|-U] [-s] <roleid/userid> <tabname> <rowid> - gain access");
                System.out.println("rmacc [-R|-U] [-s] <roleid/userid> <tabname> <rowid> - remove access");
                System.out.println("addrole <parentid> <name> - adds role");
                System.out.println("deleterole <roleid> - deletes role");
                System.out.println("adduser <employeeid> <login> <passwd> <roleid> - adds user");
                System.out.println("deleteuser <userid> - deletes user");
                System.out.println("changerole <userid> <newrole> - changes role of user");
                System.out.println("exit - logout");
            }

            //tree
            else if (len ==1 && c[0].equals("tree")) {
                this.baseManagement.printTree();
            }

            //access management
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

            //base management
            else if (len==5 && c[0].equals("adduser")) {
                this.baseManagement.addUser(Long.parseLong(c[1]),c[2],c[3],Long.parseLong(c[4]));
            }
            else if (len==3 && c[0].equals("addrole")) {
                this.baseManagement.addRole(Long.parseLong(c[1]),c[2]);
            }
            else if (len==2 && c[0].equals("deleteuser")) {
                this.baseManagement.deleteUser(Long.parseLong(c[1]));
            }
            else if (len==2 && c[0].equals("deleterole")) {
                this.baseManagement.deleteRole(Long.parseLong(c[1]));
            }
            else if (len==3 && c[0].equals("changerole")) {
                this.baseManagement.changeUserRole(Long.parseLong(c[1]), Long.parseLong(c[2]));
            }

            // failure
            else {
                throw new InvalidCommandException();
            }
        }
        catch (Exception e) {
            throw new InvalidCommandException();
        }
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Admin "+user.getLogin()+" Session");
        boolean exit = false;
        while(!exit) {
            System.out.print("> ");
            String command = sc.nextLine();
            if (!command.equals("exit")) {
                try {
                    executeCommand(command);
                } catch (InvalidCommandException e) {
                    System.out.println(e.getMessage());
                }
            }
            else {
                exit=true;
            }
        }
    }
}
