package com.piersqure.boot.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.piersqure.boot.dao.EmployeeRepository;
import com.piersqure.boot.entity.SpringBootEmployee;
import com.piersqure.boot.serviceImpl.EmployeeServiceImpl;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

	@Mock
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	private EmployeeServiceImpl employeeService;
	
	 
	@DisplayName("Junit test for save employee operations in service Layer.")
	@Test
	public void givenEmployeeObject_whenSaveEmployee_thenReturnEmployeeObject() {
	  
	  //given - precondition or setup.
		SpringBootEmployee employee = SpringBootEmployee.builder()			                      
                .name("Davis")
                .address("Bamra")
                .contact("7978841141")
                .salary(45000.00)
                .build();
	  
	   //when - action or behavior that we are going to test.
		when(employeeRepository.save(employee)).thenReturn(employee);		
		SpringBootEmployee saveEmployee = employeeService.insertEmployee(employee);
	  
	   //then - verify the output. 
		assertThat(employee).isEqualTo(saveEmployee);
		verify(employeeRepository).save(employee);
	}
	
	 @DisplayName("Junit test for get list of employee object in service Layer.")
	 @Test
	 public void givenEmployeeList_whenFindAllEmployee_thenReturnListEmployeeObject() {
	  
	  //given - precondition or setup.
	 SpringBootEmployee employee = SpringBootEmployee.builder()			                      
            .name("Nikita")
            .address("Jharsuguda")
            .contact("7978841875")
            .salary(5000.00)
            .build();
	 
	 SpringBootEmployee employee1 = SpringBootEmployee.builder()			                      
	            .name("Davis")
	            .address("Bamra")
	            .contact("7978841141")
	            .salary(95000.00)
	            .build();
	 
	 List<SpringBootEmployee> employeeList = new ArrayList<>();
	 employeeList.add(employee);
	 employeeList.add(employee1);
	  
	  //when - action or behavior that we are going to test.
	 when(employeeRepository.findAll()).thenReturn(employeeList);
	 List<SpringBootEmployee> getAllEmployee = employeeService.getEmployeeDetails();
	  
	  //then - verify the output.
	 assertThat(getAllEmployee).isNotNull();
	 assertThat(getAllEmployee).size().isGreaterThan(0);
	 assertThat(getAllEmployee).size().isEqualTo(2);
	 
	}
	 
 
	 @DisplayName("Junit test for get employee object by Id in service Layer.")
	 @Test
	 public void givenEmployeeObject_whenFindByID_thenReturnEmployeeObject() {
	  
	  //given - precondition or setup.
	 SpringBootEmployee employee = SpringBootEmployee.builder()			                      
            .name("Nikita")
            .address("Jharsuguda")
            .contact("7978841875")
            .salary(5000.00)
            .build();
	  
	  //when - action or behavior that we are going to test.
	 when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
	 SpringBootEmployee getEmployee = employeeService.getEmployeeDetailsByID(employee.getId());
	  
	  //then - verify the output.
	 assertThat(getEmployee).isNotNull();
	 verify(employeeRepository).findById(employee.getId());
	}
	 
 
	 @DisplayName("Junit test for delete employy by id in service Layer.")
	 @Test
	 public void givenEmployeeID_whendeleteEmployeeByID_thenReturnNothing() {
	  
	  //given - precondition or setup.
	 Long id = 1L;
	  //when - action or behavior that we are going to test.
	 employeeService.deleteEmployeeByID(id);
	  
	  //then - verify the output.
	 verify(employeeRepository).deleteById(id);
	}
	 
	 
	    @DisplayName("Junit test for update employee operations in service Layer.")
		@Test
		public void givenExistingEmployee_whenUpdateEmployee_thenReturnupdatedEmployee() {
		  
		  //given - precondition or setup.
		   Long id = 1L;
		 
			SpringBootEmployee existingEmployee = SpringBootEmployee.builder()			                      
	                .name("Davis")
	                .address("Bamra")
	                .contact("7978841141")
	                .salary(45000.00)
	                .build();
			
			SpringBootEmployee updatedEmployee = SpringBootEmployee.builder()			                      
	                .name("Davis")
	                .address("Bangalore")
	                .contact("7978841141")
	                .salary(45000.00)
	                .build();
		  
		  
		   //when - action or behavior that we are going to test.
			when(employeeRepository.findById(id)).thenReturn(Optional.of(existingEmployee));
			when(employeeRepository.save(any(SpringBootEmployee.class))).thenReturn(updatedEmployee);
			
			SpringBootEmployee result = employeeService.updateEmployee(id, updatedEmployee);
		  
			//then - verify the output. 
			assertEquals("Bangalore",result.getAddress());
			
			 verify(employeeRepository).findById(id);
		     verify(employeeRepository).save(existingEmployee);
		}
	    
	    @DisplayName("Junit test for update employee operations in service Layer.")
		@Test
		public void givenNonExistingEmployee_whenUpdateEmployee_thenReturnNull() {
			  
		 //given - precondition or setup.
		 Long id = 1L;

		 SpringBootEmployee updatedEmployee = SpringBootEmployee.builder()			                      
		                .name("Davis")
		                .address("Bangalore")
		                .contact("7978841141")
		                .salary(45000.00)
		                .build();
			  
			  
		//when - action or behavior that we are going to test.
		when(employeeRepository.findById(id)).thenReturn(Optional.empty());
				
		SpringBootEmployee result = employeeService.updateEmployee(id, updatedEmployee);
			  
		//then - verify the output. 
		assertNull(result);
				
		verify(employeeRepository).findById(id);
		verify(employeeRepository, never()).save(any(SpringBootEmployee.class));
			
	    }
	
	  
	  //Junit test for 
	  //public void given_when_then() {
	  
	  //given - precondition or setup.
	  
	  //when - action or behavior that we are going to test.
	  
	  //then - verify the output.
	
	  //}
	 
	 
}
