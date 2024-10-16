package com.employee.data.controller;

import com.employee.data.entity.Employee;
import com.employee.data.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class EmployeeController {
    @Autowired
  EmployeeService employeeService;


    // method to save employee
    @PostMapping("/employee")
public ResponseEntity<Employee> saveEmployeeData(@Valid @RequestBody Employee employee){
return  new ResponseEntity<>(employeeService.saveEmployee(employee),HttpStatus.CREATED);
}

// get all employees
    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return new ResponseEntity<>(employeeService.getAllEmployees(),HttpStatus.OK);
    }

    // get By emp id
    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getById(@PathVariable("id") Integer id){
return  new ResponseEntity<>(employeeService.getEmployeeById(id),HttpStatus.OK);
    }

    // update employee by passing id
    @PutMapping("employee/{id}")
    public ResponseEntity<Employee> updateEmployeeData(@Valid @PathVariable("id") Integer id, @RequestBody Employee employee){
        return new ResponseEntity<>(employeeService.updateEmployee(id, employee),HttpStatus.OK);
    }

    // delete all employee
    // delete emp by id


}
