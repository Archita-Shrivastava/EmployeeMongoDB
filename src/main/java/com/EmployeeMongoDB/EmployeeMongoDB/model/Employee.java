package com.EmployeeMongoDB.EmployeeMongoDB.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "EmpInfo")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private int empId;
    private String empName;
    private String empCity;
    private String empPhone;
}
