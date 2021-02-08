package com.project.dp.DataProviders;

import com.project.dp.Entities.ACL;

import java.util.ArrayList;
import java.util.List;

public class ACLServiceUnitTestDataProvider {

    public static final Long aclId = 1L;
    public static ACL acl;
    public static ACL acl1;
    public static ACL acl2;
    public static ACL created_acl;
    public static List<ACL> acls;
    static {
        acl = ACL.builder().aclId(aclId).userId(1L).tabName("test_name").rowId(1L).build();
        acl1 = ACL.builder().aclId(2L).userId(2L).tabName("test_name2").rowId(2L).build();
        acl2 = ACL.builder().aclId(3L).userId(3L).tabName("test_name3").rowId(3L).build();
        created_acl = ACL.builder().aclId(4L).userId(4L).tabName("test_name4").rowId(4L).build();

        acls = new ArrayList<>();
        acls.add(acl);
        acls.add(acl1);
    }
}
