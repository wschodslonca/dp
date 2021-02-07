package com.project.dp.Management;

import com.project.dp.Tree.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Management {

    Tree tree;

    @Autowired
    public Management(Tree tree) {
        this.tree = tree;
        this.tree.loadTree();
    }


}
