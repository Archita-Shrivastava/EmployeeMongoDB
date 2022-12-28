package com.EmployeeMongoDB.EmployeeMongoDB.controller;

import com.EmployeeMongoDB.EmployeeMongoDB.model.EmpExperience;
import com.EmployeeMongoDB.EmployeeMongoDB.model.EmployeeRequest;
import com.EmployeeMongoDB.EmployeeMongoDB.model.EmployeeResponse;
import com.EmployeeMongoDB.EmployeeMongoDB.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class EmpController {
    @Autowired
    private EmpService empService;

    @PostMapping("/createEmployee")
    public Mono<EmployeeResponse> createEmployee(@RequestBody @Validated EmployeeRequest employeeRequest){

        return empService.createEmployee(employeeRequest);
    }

    @GetMapping("/findEmpSkillSet")
    public Flux<EmployeeRequest> findGreaterThanExp(@RequestBody EmpExperience empExp){
        return empService.findGreaterThanExp(empExp);
    }
}
