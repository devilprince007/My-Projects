package com.life.social.service;

import java.util.List;

import com.life.social.Model.User;

public interface UserService {
	User registerUser(User user) throws Exception;
	User findUserById(Long userId) throws Exception;
	User findUserByEmail(String email);
	User followUser(Long userId, Long followerId) throws Exception;
	User updateUserDetails(User user, Long userId) throws Exception;
	List<User> searchUser(String query);
	String deleteUserById(Long id) throws Exception;
	List<User> getAllUsers();
}
