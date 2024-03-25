package com.life.social.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.life.social.Model.User;

public interface UserRepo extends JpaRepository<User, Long>{
	
	public User findByEmail(String email);
	
	@Query("SELECT u FROM User u WHERE u.firstName LIKE %:query% OR u.lastName LIKE %:query% OR u.email LIKE %:query%")
	public List<User> serchUser(String query);
}
