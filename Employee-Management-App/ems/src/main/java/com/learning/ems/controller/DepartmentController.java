package com.learning.ems.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.ems.dto.DepartmentDto;
import com.learning.ems.service.DepartmentService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
	
	private DepartmentService service;
	
	@PostMapping
	public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto){
		DepartmentDto department = service.createDepartment(departmentDto);
		
		return ResponseEntity.ok(department);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable("id") Long departmentId){
		DepartmentDto department = service.getDepartmentById(departmentId);
		
		return ResponseEntity.ok(department);
	}
	
	@GetMapping
	public ResponseEntity<List<DepartmentDto>> getAllDepartment(){
		List<DepartmentDto> department = service.getAllDepartments();
		
		return ResponseEntity.ok(department);
	}
	
    @PutMapping("{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable("id") Long departmentId,
                                                          @RequestBody DepartmentDto updatedDepartment){
        DepartmentDto departmentDto = service.updateDepartment(departmentId, updatedDepartment);
        return ResponseEntity.ok(departmentDto);
    }

    // Build Delete Department REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long departmentId){
        service.deleteDepartment(departmentId);
        return ResponseEntity.ok("Department deleted successfully!.");
    }
}
