package com.EmployeeMongoDB.EmployeeMongoDB.service;

import com.EmployeeMongoDB.EmployeeMongoDB.model.*;
import com.EmployeeMongoDB.EmployeeMongoDB.repository.EmpRepo;
import com.EmployeeMongoDB.EmployeeMongoDB.repository.EmpSkillSetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmpService {
    @Autowired
    private EmpRepo empRepo;
    @Autowired
    private EmpSkillSetRepo empSkillSetRepo;
    public Mono<EmployeeResponse> createEmployee(EmployeeRequest employeeRequest) {
        Employee employee = new Employee(employeeRequest.getEmpId(), employeeRequest.getEmpName(),
                employeeRequest.getEmpCity(), employeeRequest.getEmpPhone());
        EmployeeSkillSet employeeSkillSet = new EmployeeSkillSet(employeeRequest.getEmpId(), employeeRequest.getJavaExp(),
                employeeRequest.getSpringExp());
        return empRepo.existsByEmpId(employeeRequest.getEmpId())
                .flatMap(emp_exists -> {
                    if (emp_exists) {
                        return Mono.zip(
                                Mono.just(employee), Mono.just(employeeSkillSet)
                        ).map(t -> new EmployeeResponse(t.getT1().getEmpId(),
                                t.getT1().getEmpName(), t.getT1().getEmpCity(), t.getT1().getEmpPhone(),
                                t.getT2().getJavaExp(), t.getT2().getSpringExp(), "Already Exists"));
                    } else {
                        return Mono.zip(empRepo.save(employee), empSkillSetRepo.save(employeeSkillSet))
                                .map(t -> {
                                            return new EmployeeResponse(t.getT1().getEmpId(),
                                                    t.getT1().getEmpName(), t.getT1().getEmpCity(), t.getT1().getEmpPhone(),
                                                    t.getT2().getJavaExp(), t.getT2().getSpringExp(), "Created");
                                        }
                                );
                    }
                });

    }

    public Flux<EmployeeRequest> findGreaterThanExp(EmpExperience empExp) {
        Flux<EmployeeSkillSet> empSkillFlux = null;
        if (empExp.getSpringExp() == 0.0d) {
            empSkillFlux = empSkillSetRepo.findByJavaExpGreaterThan(empExp.getJavaExp());
        } else if (empExp.getJavaExp() == 0.0d) {
            empSkillFlux = empSkillSetRepo.findBySpringExpGreaterThan(empExp.getSpringExp());
        } else if (empExp.getJavaExp() != 0.0 && empExp.getSpringExp() != 0.0) {
            empSkillFlux = empSkillSetRepo.findByJavaExpGreaterThan(empExp.getJavaExp()).
                    filter(empSkill -> empSkill.getSpringExp() > empExp.getSpringExp());

        }
        Flux<Employee> employeeFlux = empSkillFlux.concatMap((empSkill -> {
            return this.empRepo.findByEmpId(empSkill.getEmpId());
        }));
        return Flux.zip(employeeFlux, empSkillFlux)
                .map(emp -> new EmployeeRequest(emp.getT1().getEmpId(), emp.getT1().getEmpName(),
                        emp.getT1().getEmpCity(), emp.getT1().getEmpPhone(), emp.getT2().getJavaExp(),

                        emp.getT2().getSpringExp()));
    }
    }

