package com.piersqure.boot.service;

import java.util.List;

import com.piersqure.boot.entity.SpringBootEmployee;

/**
 * EmployeeService is an interface that defines the service layer methods 
 * for managing employee-related operations. These operations include 
 * retrieving all employees, getting an employee by ID, inserting a new employee, 
 * updating an existing employee, and deleting an employee by ID.
 * 
 * Developer: Davis Nayak
 * Written on: 18 Aug 2024
 */

public interface EmployeeService {
	
	List<SpringBootEmployee> getEmployeeDetails();

	SpringBootEmployee getEmployeeDetailsByID(Long id);

	SpringBootEmployee insertEmployee(SpringBootEmployee employee);

	void deleteEmployeeByID(Long id);

	SpringBootEmployee updateEmployee(Long id,SpringBootEmployee employee);

}
