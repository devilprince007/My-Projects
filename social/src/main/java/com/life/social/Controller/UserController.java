package com.life.social.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.life.social.Model.User;
import com.life.social.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {
	
	private UserService service;

	@GetMapping("api/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Long id) throws Exception {
		return ResponseEntity.ok(service.findUserById(id));
	}
	
	@GetMapping("api/users/email/{email}")
	public ResponseEntity<User> findByEmail(@PathVariable("email") String email){
		return ResponseEntity.ok(service.findUserByEmail(email));
	}

	@PutMapping("/{userId}/{followerId}")
	public ResponseEntity<User> followUser(@PathVariable("userId") Long userId, @PathVariable("followerId") Long followerId) throws Exception{
		return ResponseEntity.ok(service.followUser(userId, followerId));
	}
	
	@PutMapping("api/users/update/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) throws Exception {		
		return ResponseEntity.ok(service.updateUserDetails(user, id));
	}
	
	@GetMapping("api/users/search/")
	public ResponseEntity<List<User>> searchUser(@RequestParam("query") String query){
		return ResponseEntity.ok(service.searchUser(query));
	}
	
	@DeleteMapping("api/users/delete/{id}")
	public ResponseEntity<String> DeleteUser(@PathVariable("id") Long id) throws Exception {
		String data = service.deleteUserById(id);
		return ResponseEntity.ok(data);
	}

	@GetMapping("api/users")
	public ResponseEntity<List<User>> getUsers() {	
		return ResponseEntity.ok(service.getAllUsers());
	}
}
