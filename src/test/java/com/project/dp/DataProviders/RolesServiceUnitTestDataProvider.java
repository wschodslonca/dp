package com.project.dp.DataProviders;

import com.project.dp.Entities.Roles;

import java.util.ArrayList;
import java.util.List;

public class RolesServiceUnitTestDataProvider {

    public static final Long roleId = 1L;
    public static Roles role;
    public static Roles role1;
    public static Roles role2;
    public static Roles created_role;
    public static List<Roles> roles;
    static {
        role = Roles.builder().roleId(roleId).roleName("test_role").parentId(0L).build();
        role1 = Roles.builder().roleId(2L).roleName("test_role2").parentId(1L).build();
        role2 = Roles.builder().roleId(3L).roleName("test_role3").parentId(2L).build();
        created_role = Roles.builder().roleId(4L).roleName("new_role").parentId(3L).build();

        roles = new ArrayList<>();
        roles.add(role);
        roles.add(role1);
    }
}
