package com.doubletex.app.api.employee;

import com.doubletex.app.errors.DbtBadRequest;
import com.doubletex.app.errors.DbtNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public Employee get(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new DbtNotFound(Employee.class, id));
    }

    public Employee post(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee raiseSalary(Long id, Double newSalary) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new DbtNotFound(Employee.class, id));
        validateSalaryRaise(employee, newSalary);
        DbtBadRequest.current().throwIfNecessary();
        employee.setSalary(newSalary);
        return employeeRepository.save(employee);
    }

    public void validateSalaryRaise(Employee employee, Double newSalary) {
        if (employee.getSalary() > newSalary) {
            DbtBadRequest.current().addValidations("salary", "New Salary isn't big enough!");
        }
    }
}