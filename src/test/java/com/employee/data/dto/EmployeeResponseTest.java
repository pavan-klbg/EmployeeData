package com.employee.data.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

 class EmployeeResponseTest {

    EmployeeResponse response=new EmployeeResponse("jagath",34);

    @Test
    void employeeFieldDetails(){
        assertEquals("jagath", response.firstName());
        assertEquals(34,response.empAge());
    }
}
