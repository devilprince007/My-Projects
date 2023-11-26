package com.learning.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.ems.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{

}
