package com.project.dp.Management;

import com.project.dp.Tree.Tree;
import org.springframework.stereotype.Component;

@Component
public class RoleStrategy extends Management implements AccessManagementStrategy {
    public RoleStrategy(Tree tree) {
        super(tree);
    }

    public void gainAccess(Long id, String table, Long record){
        tree.searchTreeByRole(id).gainAccess(table, record);
    }

    public void revokeAccess(Long id, String table, Long record){
        tree.searchTreeByRole(id).revokeAccess(table, record);
    }

    public void gainOneAccess(Long id, String table, Long record){
        tree.searchTreeByRole(id).gainOneAccess(table, record);
    }

    public void revokeOneAccess(Long id, String table, Long record){
        tree.searchTreeByRole(id).revokeOneAccess(table, record);
    }
}
