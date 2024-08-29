package com.piersqure.boot.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.piersqure.boot.entity.SpringBootEmployee;
import com.piersqure.boot.serviceImpl.EmployeeServiceImpl;

@WebMvcTest
public class EmployeeControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private EmployeeServiceImpl service;
	
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void givenEmployeeObject_whenInsertEmployee_thenReturnSavedEmployee() throws Exception {
	  
	//given - precondition or setup.
	SpringBootEmployee employee = SpringBootEmployee.builder()			                      
                .name("Davis")
                .address("Bamra")
                .contact("7978841141")
                .salary(45000.00)
                .build();
	BDDMockito.given(service.insertEmployee(ArgumentMatchers.any(SpringBootEmployee.class)))
	          .willAnswer((invocation)->invocation.getArgument(0));
	  
	//when - action or behavior that we are going to test.
	ResultActions response = mockMvc.perform(post("/api/employee/insertEmployee")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(employee)));
	
	//then - verify the output.
	response.andExpect(MockMvcResultMatchers.status().isOk())
	        .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(employee.getName())))
	        .andExpect(MockMvcResultMatchers.jsonPath("$.address", CoreMatchers.is(employee.getAddress())))
	        .andExpect(MockMvcResultMatchers.jsonPath("$.salary", CoreMatchers.is(employee.getSalary())));
	 }
	
	
	@Test
	public void givenListOfEmployee_whenGetAll_thenReturnEmployeeList() throws Exception {
	  
	//given - precondition or setup.
	List<SpringBootEmployee> listOfEmployee = new ArrayList<>();
	
	listOfEmployee.add( SpringBootEmployee.builder()			                      
            .name("Davis")
            .address("Bamra")
            .contact("7978841141")
            .salary(45000.00)
            .build());
	
	listOfEmployee.add( SpringBootEmployee.builder()			                      
            .name("Nikita")
            .address("Jharsuguda")
            .contact("8456453439")
            .salary(55000.00)
            .build());
	
	BDDMockito.given(service.getEmployeeDetails()).willReturn(listOfEmployee);
	  
	//when - action or behavior that we are going to test.
	ResultActions response = mockMvc.perform(get("/api/employee/getAll"));
						
	//then - verify the output.
	response.andExpect(MockMvcResultMatchers.status().isOk())
	        .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(listOfEmployee.size())));
	
	}
	
	
	//Junit test for get employee by ID positive test case scenario
	@Test
	public void givenEmployeeId_whenGetEmployeeByID_thenReturnEmployeeObject() throws Exception {
	  
	//given - precondition or setup.
	long id = 1L;
	
	SpringBootEmployee employee = SpringBootEmployee.builder()			                      
            .name("Davis")
            .address("Bamra")
            .contact("7978841141")
            .salary(45000.00)
            .build();
	
	BDDMockito.given(service.getEmployeeDetailsByID(id)).willReturn(employee);
	  
	//when - action or behavior that we are going to test.
		ResultActions response = mockMvc.perform(get("/api/employee/getEmployee/{id}", id));
							
		//then - verify the output.
		response.andExpect(MockMvcResultMatchers.status().isOk())
		        .andDo(MockMvcResultHandlers.print())
		        .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(employee.getName())))
		        .andExpect(MockMvcResultMatchers.jsonPath("$.address", CoreMatchers.is(employee.getAddress())))
		        .andExpect(MockMvcResultMatchers.jsonPath("$.salary", CoreMatchers.is(employee.getSalary())));
		
	}
	
	
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() throws Exception {
        // given - precondition or setup
        long id = 1L;

        SpringBootEmployee employee = SpringBootEmployee.builder()
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

        // Mock the service calls
        BDDMockito.given(service.updateEmployee(ArgumentMatchers.eq(id), ArgumentMatchers.any(SpringBootEmployee.class)))
                .willReturn(updatedEmployee);

        // when - action or behavior that we are going to test
        ResultActions response = mockMvc.perform(put("/api/employee/updateEmployee/{id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedEmployee)));

        // then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(updatedEmployee.getName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address", CoreMatchers.is(updatedEmployee.getAddress())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.salary", CoreMatchers.is(updatedEmployee.getSalary())));
    }
    
    @Test
    public void givenEmployeeId_whenDeleteEmployee_thenReturnNoContent() throws Exception {
        // given - precondition or setup
        long id = 1L;

        // Mock the service call
        BDDMockito.willDoNothing().given(service).deleteEmployeeByID(id);

        // when - action or behavior that we are going to test
        ResultActions response = mockMvc.perform(delete("/api/employee/deleteEmployee/{id}", id)
                .contentType(MediaType.APPLICATION_JSON));

        // then - verify the output
        response.andExpect(MockMvcResultMatchers.status().isNoContent())
                .andDo(print());
    }
	 
}
