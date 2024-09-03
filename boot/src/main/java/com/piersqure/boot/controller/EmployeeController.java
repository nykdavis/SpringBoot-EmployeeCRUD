package com.piersqure.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piersqure.boot.entity.SpringBootEmployee;
import com.piersqure.boot.exception.EmployeeAlreadyExistsException;
import com.piersqure.boot.exception.EmployeeNotFoundException;
import com.piersqure.boot.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

/**
 * EmployeeController is responsible for handling all the HTTP requests related
 * to employee operations such as getting all employees, getting an employee by
 * ID, inserting a new employee, updating an existing employee, and deleting an
 * employee.
 * 
 * Developer: Davis Nayak Written on: 18 Aug 2024
 */

@RestController
@RequestMapping("/api/employee")
@Slf4j
public class EmployeeController {

	@Autowired
	EmployeeService service;

	@GetMapping("/getAll")
	public ResponseEntity<List<SpringBootEmployee>> getEmployee() {
		log.info("Fetching all employees.");
		List<SpringBootEmployee> employees = service.getEmployeeDetails();
		log.info("Successfully fetched {} employees.", employees.size());
		return ResponseEntity.ok(employees);
	}

	@GetMapping("/getEmployee/{id}")
	public ResponseEntity<SpringBootEmployee> getEmployeeByID(@PathVariable Long id) {
		log.info("Fetching employee with id: {}", id);
		SpringBootEmployee employee = service.getEmployeeDetailsByID(id);
		if (employee == null) {
			log.error("Employee not found with id: {}", id);
			throw new EmployeeNotFoundException("Employee not found with id: " + id);
		}
		log.info("Successfully fetched employee with id: {}", id);
		return ResponseEntity.ok(employee);
	}

	@PostMapping("/insertEmployee")
	public ResponseEntity<SpringBootEmployee> insertEmployee(@RequestBody SpringBootEmployee employee) {
		log.info("Inserting employee with id: {}", employee.getId());
		if (service.getEmployeeDetailsByID(employee.getId()) != null) {
			log.error("Employee already exists with id: {}", employee.getId());
			throw new EmployeeAlreadyExistsException("Employee already exists with id: " + employee.getId());
		}
		SpringBootEmployee createdEmployee = service.insertEmployee(employee);
		log.info("Successfully inserted employee with id: {}", createdEmployee.getId());
		return ResponseEntity.ok(createdEmployee);
	}

	@PutMapping("updateEmployee/{id}")
	public ResponseEntity<SpringBootEmployee> updateEmployee(@PathVariable Long id,
			@RequestBody SpringBootEmployee employee) {
		log.info("Updating employee with id: {}", id);
		SpringBootEmployee updatedEmployee = service.updateEmployee(id, employee);
		log.info("Successfully updated employee with id: {}", id);
		return ResponseEntity.ok(updatedEmployee);
	}

	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
		log.info("Deleting employee with id: {}", id);
		SpringBootEmployee employee = service.getEmployeeDetailsByID(id);
		if (employee == null) {
			log.error("Employee not found with id: {}", id);
			throw new EmployeeNotFoundException("Employee not found with id: " + id);
		}
		service.deleteEmployeeByID(id);
		log.info("Successfully deleted employee with id: {}", id);
		return ResponseEntity.noContent().build();
	}

}
