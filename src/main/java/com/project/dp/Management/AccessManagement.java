package com.project.dp.Management;

import com.project.dp.Tree.Tree;

public class AccessManagement extends Management{

    public AccessManagement(Tree tree) {
        super(tree);
    }

    public void gainRoleAccess(Long roleid, String table, Long record){
        tree.searchTreeByRole(roleid).gainAccess(table, record);
    }

    public void revokeRoleAccess(Long roleid, String table, Long record){
        tree.searchTreeByRole(roleid).revokeAccess(table, record);
    }

    public void gainOneRoleAccess(Long roleid, String table, Long record){
        tree.searchTreeByRole(roleid).gainOneAccess(table, record);
    }

    public void revokeOneRoleAccess(Long roleid, String table, Long record){
        tree.searchTreeByRole(roleid).revokeOneAccess(table, record);
    }

    public void gainUserAccess(Long userid, String table, Long record){
        tree.searchTreeByUser(userid).gainAccess(table, record);
    }

    public void revokeUserAccess(Long userid, String table, Long record){
        tree.searchTreeByUser(userid).revokeAccess(table, record);
    }

    public void gainOneUserAccess(Long userid, String table, Long record){
        tree.searchTreeByUser(userid).gainOneAccess(table, record);
    }

    public void revokeOneUserAccess(Long userid, String table, Long record){
        tree.searchTreeByUser(userid).revokeOneAccess(table, record);
    }
}
