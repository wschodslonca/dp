package com.project.dp.Services;

import com.project.dp.Entities.Salaries;

import java.util.List;

public interface SalariesService {

    Salaries getSalary(Long salaryId);
    List<Salaries> getAllSalaries();
    Salaries addSalary(Salaries salary);
    void deleteSalary(Long salaryId);
}
