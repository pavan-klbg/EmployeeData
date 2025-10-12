package com.employee.data.mapper;

import com.employee.data.dto.CreateEmployeeRequest;
import com.employee.data.dto.EmployeeResponse;
import com.employee.data.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEntity(CreateEmployeeRequest request);
    EmployeeResponse toResponse(Employee employee);

}
