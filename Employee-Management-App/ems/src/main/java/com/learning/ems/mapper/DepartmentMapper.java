package com.learning.ems.mapper;

import com.learning.ems.dto.DepartmentDto;
import com.learning.ems.entity.Department;

public class DepartmentMapper {
	
	public static DepartmentDto mapToDepartmentDto(Department department) {
		return new DepartmentDto(
				department.getId(),
				department.getDepartmentName(),
				department.getDepartmentDiscription()
		);
	}
	
	public static Department mapToDepartment(DepartmentDto departmentDto) {
		return new Department(
				departmentDto.getId(),
				departmentDto.getDepartmentName(),
				departmentDto.getDepartmentDiscription()
		);
	}
}
