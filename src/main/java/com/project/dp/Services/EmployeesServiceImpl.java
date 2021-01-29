package com.project.dp.Services;

import com.project.dp.Entities.Employees;
import com.project.dp.Exceptions.Classes.NoSuchEmployeeException;
import com.project.dp.Exceptions.Classes.EmployeeAlreadyExistsException;
import com.project.dp.Repositories.EmployeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeesServiceImpl implements EmployeesService{
    private final EmployeesRepository employeesRepository;

    @Autowired
    public EmployeesServiceImpl(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    @Override
    public Employees getEmployee(Long employeeId) throws NoSuchEmployeeException {
        Optional<Employees> employeeOptional = this.employeesRepository.findById(employeeId);
        if (employeeOptional.isEmpty()){
            throw new NoSuchEmployeeException();
        }
        return employeeOptional.get();
    }

    @Override
    public List<Employees> getAllEmployees() {
        return this.employeesRepository.findAll();
    }

    @Override
    public Employees addEmployee(Employees employee) throws EmployeeAlreadyExistsException {
        if (this.employeesRepository.findByFirstName(employee.getFirstName()) != null && this.employeesRepository.findByLastName(employee.getLastName()) != null){
            throw new EmployeeAlreadyExistsException();
        }
        return this.employeesRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Long employeeId) throws NoSuchEmployeeException {
        Optional<Employees> employeeOptional = this.employeesRepository.findById(employeeId);
        if (employeeOptional.isEmpty()){
            throw new NoSuchEmployeeException();
        }
        else{
            Employees employee = employeeOptional.get();
            this.employeesRepository.delete(employee);
        }
    }
}
