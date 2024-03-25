package com.life.social.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.life.social.Model.Post;
import com.life.social.response.ApiResponse;
import com.life.social.service.PostService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {
	
	private PostService service;
	
	@PostMapping("/{userId}")
	public ResponseEntity<Post> createNewPost(@RequestBody Post post, @PathVariable("userId") Long userId) throws Exception{
		return new ResponseEntity<>(service.createNewPost(post, userId), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/post/{postId}/user/{userId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") Long postId, @PathVariable("userId") Long userId) throws Exception {
		ApiResponse res = ApiResponse.builder().message(service.deletePost(postId, userId)).status(true).build();
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Post>> findUsersPost(@PathVariable("userId") Long userId){
		return new ResponseEntity<>(service.findPostByUserId(userId), HttpStatus.OK);
	}
	
	@GetMapping("/{postId}")
	public ResponseEntity<Post> findPostByIdHandler(@PathVariable("postId") Long postId) throws Exception{
		return new ResponseEntity<>(service.findPostById(postId), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Post>> getAllPost(){
		return new ResponseEntity<>(service.getAllPost(), HttpStatus.OK);
	}
	
	@PutMapping("{postId}/user/{userId}")
	public ResponseEntity<Post> savedPostHandler(@PathVariable("postId") Long postId, @PathVariable("userId") Long userId) throws Exception{
		return new ResponseEntity<>(service.savedPost(postId, userId), HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/like/{postId}/user/{userId}")
	public ResponseEntity<Post> likePostHandler(@PathVariable("postId") Long postId, @PathVariable("userId") Long userId) throws Exception{
		return new ResponseEntity<>(service.likedPost(postId, userId), HttpStatus.ACCEPTED);
	}
}
