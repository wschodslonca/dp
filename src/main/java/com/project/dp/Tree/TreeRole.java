package com.project.dp.Tree;

import com.project.dp.Entities.Roles;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TreeRole implements Node{

    private TreeRole parent;
    private List<Node> children;
    private Roles role;

    public TreeRole(TreeRole parent, Roles role) {
        this.parent = parent;
        this.role = role;
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
        this.parent.gainAccess(table,record);
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

    }

    @Override
    public void revokeOneAccess(String table, Long record) {

    }

}
