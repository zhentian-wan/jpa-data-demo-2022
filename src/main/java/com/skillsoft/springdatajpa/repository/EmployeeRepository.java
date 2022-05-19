package com.skillsoft.springdatajpa.repository;

import org.springframework.data.repository.CrudRepository;
import com.skillsoft.springdatajpa.model.Employee;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);
    Optional<List<Employee>> findByName(String name);

    // SELECT * FROM Employee WHERE name = <name> OR email = <email>
    Optional<List<Employee>> findByNameOrEmail(String name, String email);
    Optional<List<Employee>> findByNameAndLevel(String name, int level);

    Optional<List<Employee>> findByNameIs(String name);

    Optional<List<Employee>> findByNameEquals(String name);

    Optional<List<Employee>> findByNameIsNot(String name);

    // SELECT * FROM Employee WHERE name LIKE '%<prefix>'
    Optional<List<Employee>> findByNameStartsWith(String prefix);
    Optional<List<Employee>> findByNameEndingWith(String suffix);

    Optional<List<Employee>> findByNameIgnoreCase(String name);
    Optional<List<Employee>> findByNameLike(String patern);

    Optional<Employee> findFirstByName(String name);
    Optional<Employee> findFirstByNameOrderByLevelAsc(String name);

    Optional<Employee> findFirstByOrderByEmail();

    List<Employee> findByLevelLessThan(int level);
    List<Employee> findByLevelLessThanEqual(int level);
    List<Employee> findByLevelGreaterThan(int level);
    List<Employee> findFirst2ByLevelOrderByBirthDateDesc(int level);
    List<Employee> findByBirthDateBefore(Date before);
    List<Employee> findByBirthDateAfter(Date before);
    List<Employee> findByBirthDateBetween(Date start, Date end);

    Employee findTopByBirthDateDesc();



}
