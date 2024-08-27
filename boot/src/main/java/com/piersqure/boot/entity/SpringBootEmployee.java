package com.piersqure.boot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

/**
 * SpringBootEmployee is an entity class that represents an employee in the
 * system. It is mapped to the "springbootemployee" table in the database. The
 * class includes fields for the employee's ID, name, address, contact, and
 * salary.
 * 
 * Developer: Davis Nayak Written on: 18 Aug 2024
 */

@Entity
@Table(name = "SPRINGBOOTEMPLOYEE")
@Data
@Builder
public class SpringBootEmployee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String address;
	private String contact;
	private Double salary;

	public SpringBootEmployee() {

	}

	public SpringBootEmployee(Long id, String name, String address, String contact, Double salary) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.contact = contact;
		this.salary = salary;
	}

}
