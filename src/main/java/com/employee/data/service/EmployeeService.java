package com.employee.data.service;

import com.employee.data.dto.CreateEmployeeRequest;
import com.employee.data.dto.EmployeeResponse;
import com.employee.data.entity.Employee;

import java.util.List;

public interface EmployeeService {

    EmployeeResponse saveEmployee(CreateEmployeeRequest request);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Integer id);

    Employee updateEmployee(Integer empId, Employee employee);

    void deleteEmployee(Integer id);

}
