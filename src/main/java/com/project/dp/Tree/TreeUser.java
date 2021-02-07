package com.project.dp.Tree;

import com.project.dp.Entities.ACL;
import com.project.dp.Entities.Users;
import com.project.dp.Exceptions.Classes.NoSuchACLException;
import com.project.dp.Services.ACLService;


public class TreeUser implements Node{
    private final ACLService aclService;
    private TreeRole role;
    private Users user;

    public TreeUser(ACLService aclService) {
        this.aclService = aclService;
    }

    void setUser(Users user) {
        this.user = user;
    }
    void setRole(TreeRole role) {this.role = role;}
    TreeRole getRole() {return this.role;}
    Users getUser() {return this.user;}

    @Override
    public void gainAccess(String table, Long record) {
        this.gainOneAccess(table,record);
        if (this.role.getParent()!=null) this.role.getParent().gainAccess(table,record);
    }

    @Override
    public void gainOneAccess(String table, Long record) {
        aclService.save(this.user.getUserId(), table, record);
        System.out.println("@ user "+this.user.getUserId());
    }

    @Override
    public void revokeAccess(String table, Long record) {
        this.revokeOneAccess(table,record);
        for (Node c : this.role.getChildren()) {
            if (c instanceof TreeRole) {
                c.revokeAccess(table,record);
            }
        }
    }

    @Override
    public void revokeOneAccess(String table, Long record) {
        try {
            aclService.delete(this.user.getUserId(), table, record);
            System.out.println("# user "+this.user.getUserId());
        }
        catch (NoSuchACLException ignored){}
    }

    @Override
    public String toString() {
        return "user:" + user.getUserId();
    }
}
