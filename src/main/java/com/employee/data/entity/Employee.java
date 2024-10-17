package com.employee.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Employee implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer empId;

    @NotNull(message = "first name and last name cannot be empty")
    private String firstName, lastName;

    @Email(message = "please enter valid email id")
    private String emailId;

    @Max(message = "maximum age must not exceed 60", value = 60)
    @Min(message = "minimum age is 24", value = 24)
    private Integer empAge;

    @NotNull(message = "Must not be Empty and NULL")
    private String empAddress;

    @Pattern(regexp = "\\d{10}", message = "mobile number is not valid or less than 10 digits")
    private String phoneNumber;

    @NotNull(message = "please specify active or inactive")
    private Boolean workStatus;

    @Transient
    //@NotEmpty(message = "DOJ cannot be empty")
    private Date doj;

    @NotNull(message = "please specify salary")
    private Double Salary;

    @NotNull(message = "please enter atleast one skillset")
    private List<String> technicalSkills;

}
