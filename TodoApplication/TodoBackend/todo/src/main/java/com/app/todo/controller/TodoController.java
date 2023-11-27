package com.app.todo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.todo.dto.TodoDto;
import com.app.todo.service.TodoService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/todos")
@AllArgsConstructor
public class TodoController {
	private TodoService service;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){
		return new ResponseEntity<>(service.addTodo(todoDto), HttpStatus.OK);
	}
    
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@GetMapping("{id}")
	public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long id){
		return new ResponseEntity<>(service.getTodo(id), HttpStatus.OK);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos(){
		return ResponseEntity.ok(service.getAllTodos());
	}
    
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("{id}")
	public ResponseEntity<TodoDto> updateTodo(@PathVariable("id") Long id, @RequestBody TodoDto todoDto) {
		return ResponseEntity.ok(service.updateTodo(todoDto, id));
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long id){
		service.deleteTodo(id);
		return ResponseEntity.ok("Todo Deleted Successfully!!");
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long id){
		return ResponseEntity.ok(service.completeTodo(id));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@PatchMapping("{id}/in-complete")
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable("id") Long id){
		return ResponseEntity.ok(service.inCompleteTodo(id));
	}
}
