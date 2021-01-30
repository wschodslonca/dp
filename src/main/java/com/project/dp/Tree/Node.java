package com.project.dp.Tree;

import com.project.dp.Services.ACLService;

public interface Node {
    void gainAccess(String table, Long record);
    void gainOneAccess(String table, Long record);
    void revokeAccess(String table, Long record);
    void revokeOneAccess(String table, Long record);
}
