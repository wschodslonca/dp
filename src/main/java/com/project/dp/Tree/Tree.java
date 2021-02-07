package com.project.dp.Tree;

import com.project.dp.Entities.Roles;
import com.project.dp.Entities.Users;
import com.project.dp.Exceptions.Classes.NoSuchRoleException;
import com.project.dp.Exceptions.Classes.NoSuchUserException;
import com.project.dp.Services.ACLService;
import com.project.dp.Services.RolesService;
import com.project.dp.Services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Tree {

    ACLService aclService;
    RolesService rolesService;
    UsersService usersService;
    TreeRole root;

    @Autowired
    public Tree(RolesService rolesService, UsersService usersService, ACLService aclService) {
        this.rolesService = rolesService;
        this.usersService = usersService;
        this.aclService = aclService;
    }

    public void loadTree() {
        List<Roles> initSearch = this.rolesService.findAllByParentId(0L);
        if (initSearch.size()==1) {
            Roles initRole = initSearch.get(0);
            this.root = new TreeRole(null,initRole);
            addToTree(root);
            for (Users u : usersService.getAllUsers()) {
                TreeUser newUser = new TreeUser(aclService);
                newUser.setUser(u);
                TreeRole r = searchTreeByRole(u.getRoleId());
                newUser.setRole(r);
                r.addChild(newUser);
            }
        }
        ////////////////////todo: kontrola bledu - jak sie nie zaladuje
    }

    public void printTree() {
        System.out.println(root);
        for (Node c : root.getChildren()) {
            System.out.println("|____"+c);
            if (c instanceof TreeRole) {
                printTreeRole(2,(TreeRole)c);
            }
        }
    }

    public TreeRole searchTreeByRole(Long id) {
        if (this.root.getRole().getRoleId().equals(id)) {
            return root;
        }
        else {
            for (Node n : this.root.getChildren()) {
                if (n instanceof TreeRole) {
                    TreeRole r = searchTreeByRoleNode((TreeRole) n, id);
                    if (r!=null) return r;
                }
            }
        }
        throw new NoSuchRoleException();
    }

    public TreeUser searchTreeByUser(Long id) {
        for (Node n : this.root.getChildren()) {
            if (n instanceof TreeUser) {
                TreeUser u = (TreeUser)n;
                if (u.getUser().getUserId().equals(id)) {
                    return u;
                }
            }
            else if (n instanceof TreeRole) {
                TreeUser r = searchTreeByUserNode((TreeRole) n, id);
                if (r!=null) return r;
            }
        }
        throw new NoSuchUserException();
    }

    private void addToTree(TreeRole treeRole) {
        for (Roles r : this.rolesService.findAllByParentId(treeRole.getRole().getRoleId())) {
            TreeRole newTreeRole = new TreeRole(treeRole,r);
            treeRole.addChild(newTreeRole);
            addToTree(newTreeRole);
        }
    }

    private TreeRole searchTreeByRoleNode(TreeRole treeRole, Long id) {
        if (treeRole.getRole().getRoleId().equals(id)) {
            return treeRole;
        } else {
            for (Node n : treeRole.getChildren()) {
                if (n instanceof TreeRole) {
                    TreeRole r = searchTreeByRoleNode((TreeRole) n, id);
                    if (r!=null) return r;
                }
            }
        }
        return null;
    }

    private TreeUser searchTreeByUserNode(TreeRole treeRole, Long id) {
        for (Node n : treeRole.getChildren()) {
            if (n instanceof TreeUser) {
                TreeUser u = (TreeUser)n;
                if (u.getUser().getUserId().equals(id)) {
                    return u;
                }
            }
            if (n instanceof TreeRole) {
                TreeUser r = searchTreeByUserNode((TreeRole) n, id);
                if (r!=null) return r;
            }
        }
        return null;
    }
    private void printTreeRole(int s, TreeRole treeRole) {
        for (Node c : treeRole.getChildren()) {
            System.out.print("|");
            for (int i=0;i<s-1;i++) {
                System.out.print("    |");
            }
            System.out.print("____");
            System.out.println(c);
            if (c instanceof TreeRole) {
                printTreeRole(s+1,(TreeRole)c);
            }
        }
    }

    public void addUser(Users user){
        TreeUser treeUser = new TreeUser(aclService);
        TreeRole role = this.searchTreeByRole(user.getRoleId());
        treeUser.setUser(user);
        treeUser.setRole(role);
        role.addChild(treeUser);
    }

    public void addRole(Roles role){
        TreeRole parent = this.searchTreeByRole(role.getParentId());
        TreeRole treerole = new TreeRole(parent, role);
        parent.addChild(treerole);
    }

    public void deleteUser(Long userid){
        try {
            TreeUser user = searchTreeByUser(userid);
            user.getRole().abandonChild(user);
        }catch (NoSuchUserException e){
            throw e;
        }
    }

    public void deleteRole(Long roleid){
        try {
            TreeRole role = searchTreeByRole(roleid);
            for (Node c : role.getChildren()){
                if (c instanceof TreeRole){
                    ((TreeRole) c).setParent(role.getParent());
                }
            }
            role.getParent().abandonChild(role);
        }catch (NoSuchRoleException e){
            throw e;
        }
    }
}