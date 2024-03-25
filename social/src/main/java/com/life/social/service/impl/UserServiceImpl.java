package com.life.social.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.life.social.Model.User;
import com.life.social.Repository.UserRepo;
import com.life.social.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
	
	private UserRepo repo;
	
	@Override
	public User registerUser(User user) throws Exception {
		
		User isExist = repo.findByEmail(user.getEmail());
		if(isExist != null)
			throw new Exception("This Email already used with another account");
		User savedUser = repo.save(user);
		return savedUser;
	}

	@Override
	public User findUserById(Long userId) throws Exception {
		
		User user = repo.findById(userId).orElseThrow(() -> new Exception("somethig went wrong!!!"));
		return user;
	}

	@Override
	public User findUserByEmail(String email) {
		User user = repo.findByEmail(email);
		return user;
	}

	@Override
	public User followUser(Long userId, Long followerId) throws Exception {
		User user1 = repo.findById(userId).orElseThrow(() -> new Exception("somethig went wrong!!!"));
		User user2 = repo.findById(followerId).orElseThrow(() -> new Exception("somethig went wrong!!!"));
		
		user2.getFollowers().add(user1.getId());
		user1.getFollowings().add(user2.getId());
		
		repo.save(user1);
		repo.save(user2);
		
		return user1;
	}

	@Override
	public User updateUserDetails(User user, Long userId) throws Exception {
		User fetchedUser = repo.findById(userId).orElseThrow(() -> new Exception("Something went wrong!!!"));
		
		fetchedUser.setFirstName(user.getFirstName());
		fetchedUser.setLastName(user.getLastName());
		fetchedUser.setEmail(user.getEmail());
		fetchedUser.setPassword(user.getPassword());
		fetchedUser.setGender(user.getGender());
		
		User updatedUser = repo.save(fetchedUser);
		
		return updatedUser;
	}

	@Override
	public List<User> searchUser(String query) {
		List<User> users = repo.serchUser(query);
		return users;
	}

	@Override
	public String deleteUserById(Long id) throws Exception {
		User user = repo.findById(id).orElseThrow(() ->  new Exception("User not found by id : "+id));
		if(user != null) {
			repo.delete(user);
			return "User deleted successfully";
		}
		return "Something went wrong!!!";
	}

	@Override
	public List<User> getAllUsers() {
		
		List<User> users = repo.findAll();
		
		return users;
	}


}
