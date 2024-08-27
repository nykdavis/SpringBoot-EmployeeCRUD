package com.piersqure.boot.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import com.piersqure.boot.entity.SpringBootEmployee;

import jakarta.transaction.Transactional;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class EmployeeRepositoryTests {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@AfterEach
    @Transactional
    @Rollback
    public void cleanUp() {
        employeeRepository.deleteAll();
        employeeRepository.flush();  // Ensures that the deletion is immediately executed.
    }

	// Junit test for save employee operation.
	@DisplayName("Junit test for save employee operations.")
	@Test
	public void givenEmployeeObject_whenSave_thenReturnSaveEmployee() {

		// given - precondition or setup.
		SpringBootEmployee employee = SpringBootEmployee.builder()				                   
				                        .name("Daviss")
				                        .address("Bamra")
				                        .contact("7978841141")
				                        .salary(45000.00)
				                        .build();

		// when - action or behavior that we are going to test.
		SpringBootEmployee saveEmployee = employeeRepository.save(employee);

		// then - verify the output.
		Assertions.assertThat(saveEmployee).isNotNull();
		Assertions.assertThat(saveEmployee.getId()).isGreaterThan(0);
	}

	// Junit test for
	@DisplayName("Junit test for get List of employee operations.")
	@Test
	public void givenEmployeeList_whenFindAll_thenEmployeeList() {

		// given - precondition or setup.
		SpringBootEmployee employee = SpringBootEmployee.builder()
				                      
				                       .name("Davis")
				                       .address("Bamra")
				                       .contact("7978841141")
				                       .salary(45000.00)
				                       .build();
		
		SpringBootEmployee employee1 = SpringBootEmployee.builder()	                        
				                        .name("Davis1")
				                        .address("Bamra1")
				                        .contact("7978841142")
				                        .salary(4000.00)
				                        .build();

		SpringBootEmployee employee2 = SpringBootEmployee.builder()		                         
				                         .name("Davis2")
				                         .address("Bamra2")
				                         .contact("7978841143")
				                         .salary(5000.00)
				                         .build();

		employeeRepository.save(employee);
		employeeRepository.save(employee1);
		employeeRepository.save(employee2);

		// when - action or behavior that we are going to test.
		List<SpringBootEmployee> employeeList = employeeRepository.findAll();

		// then - verify the output.
		assertThat(employeeList).isNotNull();
		assertThat(employeeList.size()).isEqualTo(3);
	}

	// Junit test for
	@DisplayName("Junit test for get employee by id.")
	@Test
	public void givenEmployeeObject_whenFindByID_thenReturnEmployeeObject() {

		// given - precondition or setup.
		SpringBootEmployee employee = SpringBootEmployee.builder()			                      
				                      .name("Davis")
				                      .address("Bamra")
				                      .contact("7978841141")
				                      .salary(45000.00)
				                      .build();
		
		employeeRepository.save(employee);

		// when - action or behavior that we are going to test.
		SpringBootEmployee saveEmployee = employeeRepository
				                       .findById(employee.getId()).get();

		// then - verify the output.
		assertThat(saveEmployee).isNotNull();
	}

	/*
	 * 
	 * //Junit test for public void given_when_then() {
	 * 
	 * //given - precondition or setup.
	 * 
	 * //when - action or behavior that we are going to test.
	 * 
	 * //then - verify the output. }
	 * 
	 */

}
