package com.project.dp.Tree;

import com.project.dp.Entities.Roles;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class TreeRole implements Node{

    private TreeRole parent;
    private List<Node> children;
    private Roles role;

    public TreeRole(TreeRole parent, Roles role) {
        this.parent = parent;
        this.role = role;
        this.children = new ArrayList<>();
    }

    TreeRole getParent() {
        return this.parent;
    }
    Roles getRole() {
        return this.role;
    }
    void addChild(Node node) {
        this.children.add(node);
    }
    List<Node> getChildren() {
        return this.children;
    }

    @Override
    public void gainAccess(String table, Long record) {
        for (Node c : this.children) {
            if (c instanceof TreeUser) {
                c.gainOneAccess(table, record);
            }
        }
        if (this.parent!=null) this.parent.gainAccess(table,record);
    }

    @Override
    public void gainOneAccess(String table, Long record) {
        for (Node c : this.children) {
            if (c instanceof TreeUser) {
                c.gainOneAccess(table, record);
            }
        }
    }

    @Override
    public void revokeAccess(String table, Long record) {
        for (Node c : this.children) {
            if (c instanceof TreeUser) {
                c.revokeOneAccess(table, record);
            }
            else if (c instanceof TreeRole) {
                c.revokeAccess(table,record);
            }
        }
    }

    @Override
    public void revokeOneAccess(String table, Long record) {
        for (Node c : this.children) {
            if (c instanceof TreeUser) {
                c.revokeOneAccess(table, record);
            }
        }
    }

    @Override
    public String toString() {
        return "role:" + role.getRoleId();
    }
}
