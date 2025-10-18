package com.employee.data.controller;

import com.employee.data.dto.CreateEmployeeRequest;
import com.employee.data.dto.EmployeeResponse;
import com.employee.data.entity.Employee;
import com.employee.data.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    // method to save employee
    @PostMapping("/employee")
    public ResponseEntity<EmployeeResponse> saveEmployeeData(@Valid @RequestBody CreateEmployeeRequest employee) {
        EmployeeResponse response = employeeService.saveEmployee(employee);
        return ResponseEntity
                .status(201)
                .body(response);
    }

    // get all employees
    @GetMapping("/employee")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    // get By emp id
    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(employeeService.getEmployeeById(id), HttpStatus.OK);
    }

    // update employee by passing id
    @PutMapping("employee/{id}")
    public ResponseEntity<Employee> updateEmployeeData(@Valid @PathVariable("id") Integer id, @RequestBody Employee employee) {
        return new ResponseEntity<>(employeeService.updateEmployee(id, employee), HttpStatus.OK);
    }

    // delete all employee


    // delete emp by id
    @DeleteMapping("employee/{id}")
    public ResponseEntity<String> deleteEmpById(@PathVariable("id") Integer id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("employee " + id + " deleted", HttpStatus.OK);
    }


}
