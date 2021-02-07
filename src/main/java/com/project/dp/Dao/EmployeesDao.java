package com.project.dp.Dao;

import com.project.dp.Entities.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesDao extends JpaRepository<Employees,Long> {
    Employees findByFirstName(String firstName);
    Employees findByLastName(String lastName);
}
