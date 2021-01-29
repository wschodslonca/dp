package com.project.dp.Services;

import com.project.dp.Entities.Salaries;
import com.project.dp.Exceptions.Classes.NoSuchSalaryException;
import com.project.dp.Repositories.SalariesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalariesServiceImpl implements SalariesService{

    private final SalariesRepository salariesRepository;

    @Autowired
    public SalariesServiceImpl(SalariesRepository salariesRepository) {
        this.salariesRepository = salariesRepository;
    }

    @Override
    public Salaries getSalary(Long rowId) throws NoSuchSalaryException {
        Optional<Salaries> salaryOptional = this.salariesRepository.findById(rowId);
        if (salaryOptional.isEmpty()){
            throw new NoSuchSalaryException();
        }
        return salaryOptional.get();
    }

    @Override
    public List<Salaries> getAllSalaries() {
        return this.salariesRepository.findAll();
    }

    @Override
    public Salaries addSalary(Salaries salary){
        return this.salariesRepository.save(salary);
    }

    @Override
    public void deleteSalary(Long rowId) throws NoSuchSalaryException {
        Optional<Salaries> salaryOptional = this.salariesRepository.findById(rowId);
        if (salaryOptional.isEmpty()){
            throw new NoSuchSalaryException();
        }
        else{
            Salaries salary = salaryOptional.get();
            this.salariesRepository.delete(salary);
        }
    }
}
