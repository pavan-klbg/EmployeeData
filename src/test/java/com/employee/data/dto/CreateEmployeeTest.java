package com.employee.data.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CreateEmployeeTest {

    @Autowired
     private Validator validator;

    @BeforeEach
    void setUp(){
        ValidatorFactory validatorFactory= Validation.buildDefaultValidatorFactory();
        validator=validatorFactory.getValidator();
    }


    @Test
    void whenAllFieldValid_NoConstraintViolations(){
        CreateEmployeeRequest employee=new CreateEmployeeRequest(
                "pavan",
                "allur",
                true,
                "pvn@abc.com",
                56,
                "4424242234",
                45232.90,
                Arrays.asList("java","sql","sap")
        );
        Set<ConstraintViolation<CreateEmployeeRequest>> violations=validator.validate(employee);
  assertTrue(violations.isEmpty());
    }

    @Test
    void whenAgeOutOfRange_thenViolation(){
        CreateEmployeeRequest employee=new CreateEmployeeRequest(
                "girish",
                "kalpe",
                false,
                "kalpe@xyz.com",
                72,
                "4424242234",
                45688.12,
                Arrays.asList("c++","aws","devops")
        );
        Set<ConstraintViolation<CreateEmployeeRequest>> violations=validator.validate(employee);
    assertEquals(1,violations.size());
assertThat(violations).isNotEmpty();
    }
}
