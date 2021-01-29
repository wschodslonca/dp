package com.project.dp.Repositories;

import com.project.dp.Entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles,Long> {
    Roles findByRoleName(String roleName);
}
