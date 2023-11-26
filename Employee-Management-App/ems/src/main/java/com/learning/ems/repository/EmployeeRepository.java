package com.learning.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.ems.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
