package com.project.dp.Management;

import com.project.dp.Entities.Roles;
import com.project.dp.Entities.Users;
import com.project.dp.Exceptions.Classes.NoSuchRoleException;
import com.project.dp.Exceptions.Classes.NoSuchUserException;
import com.project.dp.Exceptions.Classes.RoleAlreadyExistsException;
import com.project.dp.Exceptions.Classes.UserAlreadyExistsException;
import com.project.dp.Services.ACLService;
import com.project.dp.Services.RolesService;
import com.project.dp.Services.UsersService;
import com.project.dp.Tree.Tree;
import com.project.dp.Tree.TreeUser;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseManagement extends Management{

    @Autowired
    UsersService usersService;
    RolesService rolesService;
    public BaseManagement(Tree tree) {
        super(tree);
    }

    public void addUser(Long employeeid, String login, String passwd, Long roleid){
        Users user = Users.builder().employeeId(employeeid).login(login).password(passwd).roleId(roleid).build();
        try {
            usersService.addUser(user);
            tree.addUser(user);
        }catch (UserAlreadyExistsException e){
            System.out.println("User already in database");
        }
    }

    public void addRole(Long parentid, String name){
        Roles role = Roles.builder().parentId(parentid).roleName(name).build();
        try {
            rolesService.addRole(role);
            tree.addRole(role);
        }catch (RoleAlreadyExistsException e){
            System.out.println("Role already in database");
        }
    }

    public void deleteUser(Long userid){
        try {
            usersService.deleteUser(userid);
            tree.deleteUser(userid);
        }catch  (NoSuchUserException e){
            System.out.println("No such user in database");
        }
    }

    public void deleteRole(Long roleid){
        try{
            rolesService.deleteRole(roleid);
            tree.deleteRole(roleid);
        }catch (NoSuchRoleException e){
            System.out.println("No such role in database");
        }
    }
}
