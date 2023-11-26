package com.learning.ems.service;

import java.util.List;

import com.learning.ems.dto.EmployeeDto;

public interface EmployeeService {
	EmployeeDto createEmployee(EmployeeDto employeeDto);
	EmployeeDto getEmployeeById(Long employeeId);
	List<EmployeeDto> getAllEmployee();
	EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee);
	void deleteEmployee(Long employeeId);
}
