package com.doubletex.app.api.employee;

import com.doubletex.app.errors.DbtNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeAPI {

    private final EmployeeService employeeService;

    @GetMapping("/{id}")
    public Employee get(@PathVariable Long id){
        //throw new DbtNotFound(Employee.class, id);
        return employeeService.get(id).orElseThrow(()->new DbtNotFound(Employee.class, id));
    }

    @PostMapping("")
    public void post(@RequestBody @Valid Employee employee){

        employeeService.post(employee);
     @PutMapping("/{id}/raiseSalary")
        public Employee raiseSalary(@PathVariable Long id, @RequestParam Double newSalary){
            return employeeService.raiseSalary(id, newSalary);
        }
    }
}
