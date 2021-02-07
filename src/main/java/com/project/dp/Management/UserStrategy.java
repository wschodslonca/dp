package com.project.dp.Management;

import com.project.dp.Tree.Tree;
import org.springframework.stereotype.Component;

@Component
public class UserStrategy extends Management implements AccessManagement{

    public UserStrategy(Tree tree) {
        super(tree);
    }

    public void gainAccess(Long id, String table, Long record){
        tree.searchTreeByUser(id).gainAccess(table, record);
    }

    public void revokeAccess(Long id, String table, Long record){
        tree.searchTreeByUser(id).revokeAccess(table, record);
    }

    public void gainOneAccess(Long id, String table, Long record){
        tree.searchTreeByUser(id).gainOneAccess(table, record);
    }

    public void revokeOneAccess(Long id, String table, Long record){
        tree.searchTreeByUser(id).revokeOneAccess(table, record);
    }
}
