package com.employee.data.service;

import com.employee.data.entity.Employee;
import com.employee.data.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;


    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    @Cacheable(value = "records",key = "#id")
    @Override
    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findById(id).orElseThrow(()->new RuntimeException("emp not found with id "+id));
    }

    @Override
    public Employee updateEmployee(Integer id,Employee employee) {
            Employee existingEmp = getEmployeeById(id);
        existingEmp.setFirstName(employee.getFirstName());
        existingEmp.setLastName(employee.getLastName());
        existingEmp.setEmailId(employee.getEmailId());
        existingEmp.setEmpAge(employee.getEmpAge());
        existingEmp.setPhoneNumber(employee.getPhoneNumber());
        existingEmp.setSalary(employee.getSalary());
        existingEmp.setTechnicalSkills(employee.getTechnicalSkills());
        final Employee updatedEmp=employeeRepository.save(existingEmp);
        return updatedEmp;
    }

    @Override
    public void DeleteEmployee(Integer id)  {
        employeeRepository.deleteById(id);
    }
}
