package com.skillsoft.springdatajpa.repository;

import org.springframework.data.repository.CrudRepository;
import com.skillsoft.springdatajpa.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);
    Optional<List<Employee>> findByName(String name);
}
