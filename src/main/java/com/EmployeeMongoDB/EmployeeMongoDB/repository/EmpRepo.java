package com.EmployeeMongoDB.EmployeeMongoDB.repository;

import com.EmployeeMongoDB.EmployeeMongoDB.model.Employee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
@Repository
public interface EmpRepo extends ReactiveMongoRepository<Employee,Integer> {
    Mono<Boolean> existsByEmpId(int empId);
    Mono<Employee> findByEmpId(int empId);
}
