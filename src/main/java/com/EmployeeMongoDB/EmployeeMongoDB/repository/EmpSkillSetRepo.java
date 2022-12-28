package com.EmployeeMongoDB.EmployeeMongoDB.repository;

import com.EmployeeMongoDB.EmployeeMongoDB.model.Employee;
import com.EmployeeMongoDB.EmployeeMongoDB.model.EmployeeSkillSet;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
@Repository
public interface EmpSkillSetRepo extends ReactiveMongoRepository<EmployeeSkillSet,Integer> {
    Flux<EmployeeSkillSet> findByJavaExpGreaterThan(double javaExp);
    Flux<EmployeeSkillSet>  findBySpringExpGreaterThan(double javaExp);
}
