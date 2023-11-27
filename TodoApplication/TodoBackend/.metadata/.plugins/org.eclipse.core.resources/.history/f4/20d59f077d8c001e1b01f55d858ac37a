package com.app.todo.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.app.todo.Exception.ResourceNotFoundException;
import com.app.todo.dto.TodoDto;
import com.app.todo.entity.Todo;
import com.app.todo.repository.TodoRepository;
import com.app.todo.service.TodoService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

	private TodoRepository repository;
	
	private ModelMapper modelMapper;
	
	@Override
	public TodoDto addTodo(TodoDto todoDto) {
		Todo todo = modelMapper.map(todoDto, Todo.class);
		Todo savedTodo = repository.save(todo);
		TodoDto savedTodoDto = modelMapper.map(savedTodo, TodoDto.class);
		return savedTodoDto;
	}

	@Override
	public TodoDto getTodo(Long id) {
		Todo todo = repository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Todo Not Found With Id : "+id));
		return modelMapper.map(todo, TodoDto.class);
	}

	@Override
	public List<TodoDto> getAllTodos() {
		List<Todo> todos = repository.findAll();
		return todos.stream().map((todo) -> modelMapper.map(todo, TodoDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public TodoDto updateTodo(TodoDto todoDto, Long id) {
		Todo todo = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo Not Found With Id : "+id));
		
		todo.setTitle(todoDto.getTitle());
		todo.setDescription(todoDto.getDescription());
		todo.setCompleted(todoDto.isCompleted());
		
		Todo updatedTodo = repository.save(todo);
		
		return modelMapper.map(updatedTodo, TodoDto.class);
	}

	@Override
	public void deleteTodo(Long id) {
		Todo todo = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo Not Found With Id : "+id));
		repository.deleteById(id);
	}

	@Override
	public TodoDto completeTodo(Long id) {
		Todo todo = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo Not Found With Id : "+id));
		
		todo.setCompleted(Boolean.TRUE);
		
		Todo updatedTodo = repository.save(todo);
		
		return modelMapper.map(updatedTodo, TodoDto.class);
	}

	@Override
	public TodoDto inCompleteTodo(Long id) {
		Todo todo = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo Not Found With Id : "+id));
		
		todo.setCompleted(Boolean.FALSE);
		
		Todo updatedTodo = repository.save(todo);
		
		return modelMapper.map(updatedTodo, TodoDto.class);
	}
}
