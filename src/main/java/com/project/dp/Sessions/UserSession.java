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

            //exit
            if (len==1 && c[0].equals("exit")) {
                System.exit(0);
            }

            //query
            else {
                c = command.split(" ");
                if (c[0].toLowerCase().equals("select")) {
                    List<Object[]> res = queryType.query(command, user.getUserId());
                    int reslen = 0;
                    if (!res.isEmpty()) {
                        reslen = res.get(0).length;
                    }
                    for (Object[] o : res) {
                        for (int i = 0; i < reslen; i++) {
                            System.out.print(o[i] + " ");
                        }
                        System.out.println();
                    }
                }
            }
        }
        catch (Exception e) {
            throw new InvalidCommandException();
        }
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("User "+user.getLogin()+" Session");
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
