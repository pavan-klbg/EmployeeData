package com.employee.data.entity;

import jakarta.persistence.*;
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
    private String firstName;
    private String lastName;
    private String emailId;
    private Integer empAge;
    private String empAddress;
    private String phoneNumber;
    private Boolean workStatus;
    @Transient
    //@NotEmpty(message = "DOJ cannot be empty")
    private Date doj;
    private Double salary;
    private List<String> technicalSkills;

}
