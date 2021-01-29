package com.project.dp.Repositories;

import com.project.dp.Entities.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesRepository extends JpaRepository<Employees,Long> {
    Employees findByFirstName(String firstName);
    Employees findByLastName(String lastName);
}
