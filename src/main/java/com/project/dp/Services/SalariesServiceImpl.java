package com.project.dp.Services;

import com.project.dp.Entities.Salaries;
import com.project.dp.Exceptions.Classes.NoSuchSalaryException;
import com.project.dp.Dao.SalariesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalariesServiceImpl implements SalariesService{

    private final SalariesDao salariesDao;

    @Autowired
    public SalariesServiceImpl(SalariesDao salariesDao) {
        this.salariesDao = salariesDao;
    }

    @Override
    public Salaries getSalary(Long rowId) throws NoSuchSalaryException {
        Optional<Salaries> salaryOptional = this.salariesDao.findById(rowId);
        if (salaryOptional.isEmpty()){
            throw new NoSuchSalaryException();
        }
        return salaryOptional.get();
    }

    @Override
    public List<Salaries> getAllSalaries() {
        return this.salariesDao.findAll();
    }

    @Override
    public Salaries addSalary(Salaries salary){
        return this.salariesDao.save(salary);
    }

    @Override
    public void deleteSalary(Long rowId) throws NoSuchSalaryException {
        Optional<Salaries> salaryOptional = this.salariesDao.findById(rowId);
        if (salaryOptional.isEmpty()){
            throw new NoSuchSalaryException();
        }
        else{
            Salaries salary = salaryOptional.get();
            this.salariesDao.delete(salary);
        }
    }
}
