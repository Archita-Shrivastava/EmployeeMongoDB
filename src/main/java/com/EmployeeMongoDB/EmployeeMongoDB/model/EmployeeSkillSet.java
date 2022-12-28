package com.EmployeeMongoDB.EmployeeMongoDB.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "EmpInfo")
public class EmployeeSkillSet {
    private int empId;
    private double javaExp;
    private double springExp;
}
