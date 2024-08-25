package com.piersqure.boot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.piersqure.boot.entity.SpringBootEmployee;

/**
 * EmployeeRepository is an interface that extends JpaRepository 
 * to provide CRUD operations for the SpringBootEmployee entity.
 * It acts as a Data Access Layer for managing employee data in the database.
 * 
 * Developer: Davis Nayak
 * Written on: 18 Aug 2024
 */

@Repository
public interface EmployeeRepository extends JpaRepository<SpringBootEmployee,Long> {

}
