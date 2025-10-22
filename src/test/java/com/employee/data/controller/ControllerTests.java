package com.employee.data.controller;

import com.employee.data.dto.CreateEmployeeRequest;
import com.employee.data.dto.EmployeeResponse;
import com.employee.data.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@WebMvcTest(EmployeeController.class)
class ControllerTests {

    @MockitoBean
    private EmployeeService employeeService;


    @Autowired
    MockMvc mockMvc;


    @Test
    void create_Valid_EmployeeTest() throws Exception {

        EmployeeResponse response = new EmployeeResponse("pavan", 26);

        when(employeeService.saveEmployee(any(CreateEmployeeRequest.class)))
                .thenReturn(response);
        mockMvc.perform(post("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "firstName": "pavan",
                                    "lastName": "Kumar",
                                    "workStatus": true,
                                    "emailId": "pavan.kumar@example.com",
                                    "empAge": 26,
                                    "phoneNumber": "9876543210",
                                    "Salary": 55000.75,
                                    "technicalSkills": ["Java", "Spring Boot", "SQL"]
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName").value("pavan"))
                .andExpect(jsonPath("$.empAge").value(26));


    }
    @Test
    void create_NotValid_EmployeeTest() throws Exception {

        EmployeeResponse response = new EmployeeResponse("pavan", 21);

        when(employeeService.saveEmployee(any(CreateEmployeeRequest.class)))
                .thenReturn(response);
        mockMvc.perform(post("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "firstName": "pavan",
                                    "lastName": "Kumar",
                                    "workStatus": true,
                                    "emailId": "pavan.kumar@example.com",
                                    "empAge": 21,
                                    "phoneNumber": "9876543210",
                                    "Salary": 55000.75,
                                    "technicalSkills": ["Java", "Spring Boot", "SQL"]
                                }
                                """))
                .andExpect(status().isBadRequest());

    }
}
