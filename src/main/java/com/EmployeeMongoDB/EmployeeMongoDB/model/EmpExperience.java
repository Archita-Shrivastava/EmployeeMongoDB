package com.EmployeeMongoDB.EmployeeMongoDB.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "EmpInfo")
public class EmpExperience {
    private double javaExp;
    private double springExp;
}
