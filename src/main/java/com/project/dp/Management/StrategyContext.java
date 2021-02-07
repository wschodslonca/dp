package com.project.dp.Management;

import org.springframework.stereotype.Component;

@Component
public class StrategyContext {
    private AccessManagement accessManagement;

    public void setStrategy(AccessManagement accessManagement) {
        this.accessManagement = accessManagement;
    }

    public void gainAccess(Long id, String table, Long record) {
        accessManagement.gainAccess(id,table,record);
    }
    public void revokeAccess(Long id, String table, Long record) {
        accessManagement.revokeAccess(id,table,record);
    }
    public void gainOneAccess(Long id, String table, Long record) {
        accessManagement.gainOneAccess(id,table,record);
    }
    public void revokeOneAccess(Long id, String table, Long record) {
        accessManagement.revokeOneAccess(id,table,record);
    }
}
