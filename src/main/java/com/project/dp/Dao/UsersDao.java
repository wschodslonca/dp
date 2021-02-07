package com.project.dp.Dao;

import com.project.dp.Entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersDao extends JpaRepository<Users,Long> {
    Users findByLogin(String login);
    List<Users> findAllByRoleId(Long roleId);
}
