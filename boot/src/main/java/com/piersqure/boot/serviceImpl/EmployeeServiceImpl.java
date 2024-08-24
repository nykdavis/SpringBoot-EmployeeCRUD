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

	@Override
	public SpringBootEmployee insertEmployee(SpringBootEmployee employee) {
		return employeeRepository.save(employee);
		
	}

	@Override
	public void deleteEmployeeByID(Long id) {
		employeeRepository.deleteById(id);
		
	}
	
	//employee -> for update
	//employyeFromDB  -> from DB

	@Override
	public SpringBootEmployee updateEmployee(Long id,SpringBootEmployee employee) {
		SpringBootEmployee employeeFromDB = employeeRepository.findById(id).orElse(null);
		if(employeeFromDB !=null) {
			employeeFromDB.setAddress(employee.getAddress());
			employeeFromDB.setContact(employee.getContact());
			employeeFromDB.setName(employee.getName());
			employeeFromDB.setSalary(employee.getSalary());
			return employeeRepository.save(employeeFromDB);
		}
		return null;
	}

}
