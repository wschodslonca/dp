package com.project.dp.Services;

import com.project.dp.Entities.Employees;

import java.util.List;

public interface EmployeesService {
    Employees getEmployee(Long employeeId);
    List<Employees> getAllEmployees();
    Employees addEmployee(Employees employee);
    void deleteEmployee(Long employeeId);
}
