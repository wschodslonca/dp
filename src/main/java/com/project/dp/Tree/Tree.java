package com.project.dp.Tree;

import com.project.dp.Entities.Roles;
import com.project.dp.Entities.Users;
import com.project.dp.Services.ACLService;
import com.project.dp.Services.RolesService;
import com.project.dp.Services.UsersService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Tree {
    ACLService aclService;
    RolesService rolesService;
    UsersService usersService;
    TreeRole root;

    public Tree(RolesService rolesService, UsersService usersService, ACLService aclService) {
        this.rolesService = rolesService;
        this.usersService = usersService;
        this.aclService = aclService;
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
        }
        else {
            for (Node n : treeRole.getChildren()) {
                if (n instanceof TreeRole) {
                    return searchTreeByRoleNode((TreeRole) n, id);
                }
            }
        }
        return null;
    }
    TreeRole searchTreeByRole(Long id) {
        if (this.root.getRole().getRoleId().equals(id)) {
            return root;
        }
        else {
            for (Node n : this.root.getChildren()) {
                if (n instanceof TreeRole) {
                    return searchTreeByRoleNode((TreeRole)n,id);
                }
            }
        }
        return null;
    }

    void loadTree() {
        List<Roles> initSearch = this.rolesService.findAllByParentId(0L);
        if (initSearch.size()==1) {
            Roles initRole = initSearch.get(0);
            this.root = new TreeRole(null,initRole);
            addToTree(root);
        }
        for (Users u : usersService.getAllUsers()) {
            TreeUser newUser = new TreeUser(aclService);
            newUser.setUser(u);
            searchTreeByRole(u.getRoleId()).addChild(newUser); ////////????? konstruktor moze nei difdjfdasj
        }

        ////////////////////todo: kontrola bledu
    }

}