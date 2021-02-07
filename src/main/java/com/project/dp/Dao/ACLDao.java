package com.project.dp.Dao;

import com.project.dp.Entities.ACL;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ACLDao extends JpaRepository<ACL,Long> {
    Optional<ACL> findByUserIdAndTabNameAndRowId(Long userId, String tabName, Long rowId);
}
