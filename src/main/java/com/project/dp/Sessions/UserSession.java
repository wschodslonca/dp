package com.project.dp.Sessions;
import com.project.dp.Entities.Users;
import com.project.dp.Exceptions.Classes.InvalidCommandException;
import com.project.dp.Filter.QueryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Scanner;

@Component
public class UserSession implements Session{

    Users user;
    QueryType queryType;

    @Autowired
    public UserSession(QueryType queryType) {
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

            c = command.split(" ");

            //man
            if (len==1 && c[0].equals("man")) {
                System.out.println("select... - start query with it");
                System.out.println("exit - logout");
            }

            else if (c[0].toLowerCase().equals("select")) {
                List<Object[]> res = queryType.query(command,user.getUserId());
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
            // failure
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
        System.out.println("User "+user.getLogin()+" Session");
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
