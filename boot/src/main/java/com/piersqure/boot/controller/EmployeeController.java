package com.piersqure.boot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
	
}
