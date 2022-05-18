package com.skillsoft.springdatajpa;

import com.skillsoft.springdatajpa.model.Employee;
import com.skillsoft.springdatajpa.repository.EmployeeRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SpringdatajpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringdatajpaApplication.class, args);


	}


	@Bean
	public CommandLineRunner EmployeeDemo(EmployeeRepository employeeRepository) {
		return (args) -> {
			employeeRepository.save(new Employee("Renee Bauer", "reene_bauer@exmaple.com"));
			employeeRepository.save(new Employee("Raj Chawanda", "raj_chawanda@exmaple.com"));
			employeeRepository.save(new Employee("Neil Parks", "neil_parks@exmaple.com"));
			employeeRepository.save(new Employee("Marcia Lin", "Marcia_lin@exmaple.com"));
			employeeRepository.save(new Employee("Marcia Lin", "Marcia_lina2@exmaple.com"));
			// Save a record

			//findByMethodsDemo(employeeRepository);
			//updateAndDelete(employeeRepository)
		};
	}

	private void findByMethodsDemo(EmployeeRepository employeeRepository) {
		// SELECT * form Empployee
		System.out.println("\nRetrieving all employee data..");
		for (Employee em : employeeRepository.findAll()) {
			System.out.println(em.toString());
		}

		// SELECT * FROM Employee WHERE id = 1
		System.out.println("\nRetrieving one employee data..");
		Optional<Employee> oem = employeeRepository.findById(1L);
		if (!oem.isEmpty()) {
			Employee em = oem.get();
			System.out.println(em.toString());
		}

		// SELECT * FROM Employee WHERE email = xxxx
		System.out.println("\nRetrieving one employee data by email..");
		Employee em2 = employeeRepository.findByEmail("neil_parks@exmaple.com").get();
		System.out.println(em2.toString());

		System.out.println("\nRetrieving multi employees data by name..");
		List<Employee> employees = employeeRepository.findByName("Marcia Lin").get();
		for (Employee ee : employees) {
			System.out.println(ee.toString());
		}

		System.out.println("\nRetrieving employee not exists..");
		Optional<Employee> em3 = employeeRepository.findByEmail("neil_park@exmaple.com");
		if (em3.isPresent()) {
			Employee retrivedEmp = em3.get();
		}
	}

	private void updateAndDelete(EmployeeRepository employeeRepository) {
		Optional<Employee> em2 = employeeRepository.findByEmail("neil_parks@exmaple.com");
		if (em2.isPresent()) {
			Employee ee = em2.get();
			ee.setEmail("neil_parks@gmail.com");
			employeeRepository.save(ee);
		}

		Optional<Employee> em3 = employeeRepository.findByEmail("Marcia_lina2@exmaple.com");
		if (em3.isPresent()) {
			Employee ee3 = em3.get();
			employeeRepository.delete(ee3);
			employeeRepository.deleteById(1L);
		}
	}
}
