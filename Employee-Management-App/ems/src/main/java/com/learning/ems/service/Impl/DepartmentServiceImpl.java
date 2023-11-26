package com.learning.ems.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.learning.ems.dto.DepartmentDto;
import com.learning.ems.entity.Department;
import com.learning.ems.exceptions.ResourceNotFoundException;
import com.learning.ems.mapper.DepartmentMapper;
import com.learning.ems.repository.DepartmentRepository;
import com.learning.ems.service.DepartmentService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{
	private DepartmentRepository repository;
	
	@Override
	public DepartmentDto createDepartment(DepartmentDto departmentDto) {

		Department department = DepartmentMapper.mapToDepartment(departmentDto);
		Department savedDepartment = repository.save(department);

		return DepartmentMapper.mapToDepartmentDto(savedDepartment);
	}

	@Override
	public DepartmentDto getDepartmentById(Long departmentId) {
		Department department = repository.findById(departmentId).orElseThrow(
				() -> new ResourceNotFoundException("Department is not exists with a given id: " + departmentId)
				);
		
		return DepartmentMapper.mapToDepartmentDto(department);
	}

	@Override
	public List<DepartmentDto> getAllDepartments() {
		List<Department> departments = repository.findAll();
		return departments.stream().map((department) -> DepartmentMapper.mapToDepartmentDto(department)).collect(Collectors.toList());
	}

    @Override
    public DepartmentDto updateDepartment(Long departmentId, DepartmentDto updatedDepartment) {

        Department department = repository.findById(departmentId).orElseThrow(
                () -> new ResourceNotFoundException("Department is not exists with a given id:"+ departmentId)
        );

        department.setDepartmentName(updatedDepartment.getDepartmentName());
        department.setDepartmentDiscription(updatedDepartment.getDepartmentDiscription());

        Department savedDepartment = repository.save(department);

        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public void deleteDepartment(Long departmentId) {
    	repository.findById(departmentId).orElseThrow(
                () -> new ResourceNotFoundException("Department is not exists with a given id: " + departmentId)
        );

        repository.deleteById(departmentId);
    }
}
