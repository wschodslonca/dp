package com.project.dp.Dao;

import com.project.dp.Entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RolesDao extends JpaRepository<Roles,Long> {
    Roles findByRoleName(String roleName);
    List<Roles> findAllByParentId(Long parentId);

}
