package com.project.dp.Management;

public interface AccessManagementStrategy {

    void gainAccess(Long id, String table, Long record);
    void revokeAccess(Long id, String table, Long record);
    void gainOneAccess(Long id, String table, Long record);
    void revokeOneAccess(Long id, String table, Long record);
}
