package com.project.dp.Services;

import com.project.dp.Entities.ACL;

import java.util.List;

public interface ACLService {
    ACL getACL(Long aclId);
    List<ACL> getAllACL();
    ACL addACL(ACL acl);
    void deleteACL(Long aclId);
}
