package com.project.dp.Services;

import com.project.dp.Entities.Employees;
import com.project.dp.Exceptions.Classes.NoSuchEmployeeException;
import com.project.dp.Exceptions.Classes.EmployeeAlreadyExistsException;
import com.project.dp.Dao.EmployeesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeesServiceImpl implements EmployeesService{
    private final EmployeesDao employeesDao;

    @Autowired
    public EmployeesServiceImpl(EmployeesDao employeesDao) {
        this.employeesDao = employeesDao;
    }

    @Override
    public Employees getEmployee(Long employeeId) throws NoSuchEmployeeException {
        Optional<Employees> employeeOptional = this.employeesDao.findById(employeeId);
        if (employeeOptional.isEmpty()){
            throw new NoSuchEmployeeException();
        }
        return employeeOptional.get();
    }

    @Override
    public List<Employees> getAllEmployees() {
        return this.employeesDao.findAll();
    }

    @Override
    public Employees addEmployee(Employees employee) throws EmployeeAlreadyExistsException {
        if (this.employeesDao.findByFirstName(employee.getFirstName()) != null && this.employeesDao.findByLastName(employee.getLastName()) != null){
            throw new EmployeeAlreadyExistsException();
        }
        return this.employeesDao.save(employee);
    }

    @Override
    public void deleteEmployee(Long employeeId) throws NoSuchEmployeeException {
        Optional<Employees> employeeOptional = this.employeesDao.findById(employeeId);
        if (employeeOptional.isEmpty()){
            throw new NoSuchEmployeeException();
        }
        else{
            Employees employee = employeeOptional.get();
            this.employeesDao.delete(employee);
        }
    }
}
