package com.project.dp.Services;

import com.project.dp.Entities.Roles;

import java.util.List;

public interface RolesService {

    Roles getRole(Long roleId);
    List<Roles> getAllRoles();
    Roles addRole(Roles role);
    void deleteRole(Long roleId);
}
