package com.doubletex.app.api.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public Optional<Employee> get(Long id){
        return employeeRepository.findById(id);
    }

    public void post(Employee employee){
        employeeRepository.save(employee);
    }

    public Employee raiseSalary(Long id, Double newSalary){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new DbtNotFound(Employee.class, id));
        validateSalaryRaise(employee, newSalary);
        DbtBadRequest.current().throwIfNecessary();
        employee.setSalary(newSalary);
        return employeeRepository.save(employee);
    }

    public void validateSalaryRaise(Employee employee, Double newSalary){
        DbtBadRequest dbtBadRequest = DbtBadRequest.current();
    }
        if(employee.getSalary() > newSalary){
            DbtBadRequest.current().addValidation("salary", "New Salary isn't big enough!");
        }
    }

 