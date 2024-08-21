package com.piersqure.boot.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.piersqure.boot.dao.EmployeeRepository;
import com.piersqure.boot.entity.SpringBootEmployee;
import com.piersqure.boot.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public List<SpringBootEmployee> getEmployeeDetails() {		
		return employeeRepository.findAll();
	}

	@Override
	public SpringBootEmployee getEmployeeDetailsByID(Long id) {		
		return employeeRepository.findById(id).orElse(null);
	}

}
