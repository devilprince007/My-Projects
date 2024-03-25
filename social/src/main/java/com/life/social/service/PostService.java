package com.life.social.service;

import java.util.List;

import com.life.social.Model.Post;

public interface PostService {
	
	Post createNewPost(Post post, Long userId) throws Exception;
	String deletePost(Long postId, Long userId) throws Exception ;
	List<Post> findPostByUserId(Long userId);
	Post findPostById(Long postId) throws Exception;
	List<Post> getAllPost();
	Post savedPost(Long postId, Long userId) throws Exception;
	Post likedPost(Long postId, Long userId) throws Exception;
}
