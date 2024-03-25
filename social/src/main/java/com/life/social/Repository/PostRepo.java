package com.life.social.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.life.social.Model.Post;

public interface PostRepo extends JpaRepository<Post, Long>{
	
	@Query("SELECT p FROM Post p WHERE p.user.id=:userId")
	List<Post> findPostByUserId(Long userId);
}
