package com.employee.data.dto;

import jakarta.validation.constraints.*;

import java.util.List;

public record CreateEmployeeRequest(
        @NotBlank String firstName,
        String lastName,
        @NotNull Boolean workStatus,
        @Email(message = "please enter valid email id")
        String emailId,
        @Max(message = "maximum age must not exceed 60", value = 60)
        @Min(message = "minimum age is 24", value = 24)
        Integer empAge,
        @Pattern(regexp = "\\d{10}", message = "mobile number is not valid or less than 10 digits")
        String phoneNumber,
        @NotNull(message = "please specify salary")
        Double Salary,
        @NotNull(message = "please enter atleast one skillset")
        List<String> technicalSkills) {
}
