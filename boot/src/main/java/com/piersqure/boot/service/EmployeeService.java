package com.piersqure.boot.service;

import java.util.List;

import com.piersqure.boot.entity.SpringBootEmployee;

public interface EmployeeService {
	
	List<SpringBootEmployee> getEmployeeDetails();

	SpringBootEmployee getEmployeeDetailsByID(Long id);

	SpringBootEmployee insertEmployee(SpringBootEmployee employee);

	void deleteEmployeeByID(Long id);

	SpringBootEmployee updateEmployee(Long id,SpringBootEmployee employee);

}
