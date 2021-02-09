package com.project.dp.Services;

import com.project.dp.Entities.Users;

import java.util.List;

public interface UsersService {

    Users getUser(Long userId);
    List<Users> getAllUsers();
    Users addUser(Users user);
    void deleteUser(Long userId);
    Users findByLogin(String login);
    List<Users> findAllByRoleId(Long roleId);
    void changeRole(Long userid, Long roleid);
}
