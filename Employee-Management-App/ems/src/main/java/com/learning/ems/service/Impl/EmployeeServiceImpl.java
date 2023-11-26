package com.learning.ems.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.learning.ems.dto.EmployeeDto;
import com.learning.ems.entity.Department;
import com.learning.ems.entity.Employee;
import com.learning.ems.exceptions.ResourceNotFoundException;
import com.learning.ems.mapper.EmployeeMapper;
import com.learning.ems.repository.DepartmentRepository;
import com.learning.ems.repository.EmployeeRepository;
import com.learning.ems.service.EmployeeService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeRepository repository;
	private DepartmentRepository departmentRepository;

	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		
		Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
		Department department = departmentRepository.findById(employeeDto.getDepartmentId())
				.orElseThrow(() -> new ResourceNotFoundException("Department Doesn't Exist"));
		
		employee.setDepartment(department);
		Employee savedEmployee = repository.save(employee);
		return EmployeeMapper.mapToEmployeeDto(savedEmployee);
	}

	@Override
	public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = repository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee is not exists with given id : " + employeeId));

        return EmployeeMapper.mapToEmployeeDto(employee);
	}

	@Override
	public List<EmployeeDto> getAllEmployee() {
        List<Employee> employees = repository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());
	}

	@Override
	public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
		
		Employee employee = repository.findById(employeeId).orElseThrow(
				() -> new ResourceNotFoundException("Employee doesnt exist with given id : "+employeeId)
			);
		
		Department department = departmentRepository.findById(updatedEmployee.getDepartmentId())
				.orElseThrow(() -> new ResourceNotFoundException("Department Doesn't Exist"));
		
		employee.setFirstName(updatedEmployee.getFirstName());
		employee.setLastName(updatedEmployee.getLastName());
		employee.setEmail(updatedEmployee.getEmail());
		employee.setDepartment(department);
		
		Employee updatedEmployeeObj = repository.save(employee);
		
		return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
	}

	@Override
	public void deleteEmployee(Long employeeId) {
		Employee employee = repository.findById(employeeId).orElseThrow(
					() -> new ResourceNotFoundException("Employee doesnt exist with given id : "+employeeId)
				);
		
		repository.delete(employee);	
	}
}
