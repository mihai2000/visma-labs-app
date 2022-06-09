package com.doubletex.app.api.employee;

import com.doubletex.app.errors.DbtBadRequest;
import com.doubletex.app.errors.DbtNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


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
    public Employee put (Employee employee) {
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
    public List<Employee> fetchPaginated(Integer pageSize, Integer pageNumber, String sortBy) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return employeeRepository.findAll(pageable).getContent();
    }
    public Page<Employee> search(
            Integer pageNumber,
            Integer pageSize,
            String sortBy,
            String name
    ){
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return employeeRepository.findEmployeeByFullNameLike(pageable, name);
    }
}