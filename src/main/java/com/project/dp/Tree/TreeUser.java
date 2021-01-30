package com.project.dp.Tree;

import com.project.dp.Entities.ACL;
import com.project.dp.Entities.Users;
import com.project.dp.Services.ACLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TreeUser implements Node{
    private final ACLService aclService;
    private TreeRole role;
    private Users user;

    @Autowired
    public TreeUser(ACLService aclService) {
        this.aclService = aclService;
    }

    void setUser(Users user) {
        this.user = user;
    }

    @Override
    public void gainAccess(String table, Long record) {
        this.gainOneAccess(table,record);
        this.role.getParent().gainAccess(table,record);
    }

    @Override
    public void gainOneAccess(String table, Long record) {
        ACL acl = new ACL();
        acl.setUserId(this.user.getUserId());
        acl.setTabName(table);
        acl.setRowId(record);
        aclService.addACL(acl);
    }

    @Override
    public void revokeAccess(String table, Long record) {

    }

    @Override
    public void revokeOneAccess(String table, Long record) {

    }
}
