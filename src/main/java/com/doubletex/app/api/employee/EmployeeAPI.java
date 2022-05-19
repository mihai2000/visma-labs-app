package com.doubletex.app.api.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeAPI {

    private final EmployeeService employeeService;

    @GetMapping("/{id}")
    public Employee get(@PathVariable Long id){
        //throw new DbtNotFound(Employee.class, id);
        return employeeService.get(id);
    }

    @PostMapping("")
    public Employee post(@Valid @RequestBody Employee employee){
        return employeeService.post(employee);
    }

    @PutMapping("/{id}/raiseSalary")
    public Employee raiseSalary(@PathVariable Long id, @RequestParam Double newSalary){
        var result = employeeService.raiseSalary(id,newSalary);
        return result;
    }
}
