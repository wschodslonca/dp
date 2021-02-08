package com.project.dp.Tree;

public interface Node {
    void gainAccess(String table, Long record);
    void gainOneAccess(String table, Long record);
    void revokeAccess(String table, Long record);
    void revokeOneAccess(String table, Long record);
}
