package com.project.dp.Services;

import com.project.dp.Entities.ACL;

import java.util.List;

public interface ACLService {
    ACL getACL(Long aclId);
    List<ACL> getAllACL();
    void save(Long userId, String table, Long record);
    void deleteACL(Long aclId);
    public void delete(Long userId, String table, Long record);
}
