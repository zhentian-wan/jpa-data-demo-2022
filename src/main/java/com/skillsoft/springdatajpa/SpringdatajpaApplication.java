package com.skillsoft.springdatajpa;

import com.skillsoft.springdatajpa.model.Employee;
import com.skillsoft.springdatajpa.repository.EmployeeRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;
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

		};
	}

	private void findByMethodsDemo(EmployeeRepository employeeRepository) {
		employeeRepository.save(new Employee("Renee Bauer", "reene_bauer@exmaple.com", 2, new SimpleDateFormat("dd/MM/yyyy").parse("12/24/1994")));
		employeeRepository.save(new Employee("Raj Chawanda", "raj_chawanda@exmaple.com", 3, new SimpleDateFormat("dd/MM/yyyy").parse("05/50/1988")));
		employeeRepository.save(new Employee("Neil Parks", "neil_parks@exmaple.com", 3, new SimpleDateFormat("dd/MM/yyyy").parse("13/04/1989")));
		employeeRepository.save(new Employee("Marcia Lin", "Marcia_lin@exmaple.com", 4, new SimpleDateFormat("dd/MM/yyyy").parse("09/04/1988")));
		employeeRepository.save(new Employee("Marcia Lin", "Marcia_lin-4@exmaple.com", 2, new SimpleDateFormat("dd/MM/yyyy").parse("12/06/1997")));
		employeeRepository.save(new Employee("Sakura Otto", "sakura_otto@exmaple.com", 4, new SimpleDateFormat("dd/MM/yyyy").parse("06/06/1988")));
		employeeRepository.save(new Employee("Lu Lin", "lu_lin@exmaple.com", 3, new SimpleDateFormat("dd/MM/yyyy").parse("05/05/1984")));
		employeeRepository.save(new Employee("Kewiss Diallonics", "kewiss_diallonics@exmaple.com", 3, new SimpleDateFormat("dd/MM/yyyy").parse("08/11/1992")));
		employeeRepository.save(new Employee("Ryan Sato", "ryan_sato@exmaple.com", 2, new SimpleDateFormat("dd/MM/yyyy").parse("09/03/1988")));

		//findByMethodsDemo(employeeRepository);
		//updateAndDelete(employeeRepository)

		List<Employee> ems = employeeRepository.findByNameOrEmail("Marcia Lin", "ryan_sato@exmaple.com").get();
		employeeRepository.findByNameStartsWith("R");
		employeeRepository.findByNameEndingWith("s");
		employeeRepository.findByNameIgnoreCase("LIN");
		employeeRepository.findByNameLike("%is%");
		employeeRepository.findFirstByName("Marcia Lin");


		Date refDate = new SimpleDateFormat("dd/MM/yyyy").parse("08/05/1994");
		Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse("08/05/1988");
		Employee emps = employeeRepository.findByBirthDateBetween(startDate, refDate);
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
