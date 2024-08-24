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
import com.piersqure.boot.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService service;
	
	@GetMapping("/getAll")
	public ResponseEntity<List<SpringBootEmployee>> getEmployee() {
		List<SpringBootEmployee> employees = service.getEmployeeDetails();
		return ResponseEntity.ok(employees);		
	}
	
	@GetMapping("/getEmployee/{id}")
	public ResponseEntity<SpringBootEmployee> getEmployeeByID(@PathVariable Long id){
		SpringBootEmployee employee = service.getEmployeeDetailsByID(id);
		return ResponseEntity.ok(employee);
	}
	
	@PostMapping("/insertEmployee")
	public ResponseEntity<SpringBootEmployee> insertEmployee(@RequestBody SpringBootEmployee employee){
		SpringBootEmployee createdEmployee = service.insertEmployee(employee);
		return ResponseEntity.ok(createdEmployee);
	}
	
	@PutMapping("updateEmployee/{id}")
	public ResponseEntity<SpringBootEmployee> updateEmployee(@PathVariable Long id, @RequestBody SpringBootEmployee employee){
		SpringBootEmployee updatedEmployee = service.updateEmployee(id, employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	
	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
		service.deleteEmployeeByID(id);
		return ResponseEntity.noContent().build();		
	}
	
}
