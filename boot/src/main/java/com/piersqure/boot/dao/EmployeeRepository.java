package com.piersqure.boot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.piersqure.boot.entity.SpringBootEmployee;

@Repository
public interface EmployeeRepository extends JpaRepository<SpringBootEmployee,Long> {

}
