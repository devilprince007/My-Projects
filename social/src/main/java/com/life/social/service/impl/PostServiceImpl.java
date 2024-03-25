package com.life.social.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.life.social.Model.Post;
import com.life.social.Model.User;
import com.life.social.Repository.PostRepo;
import com.life.social.Repository.UserRepo;
import com.life.social.service.PostService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService{

	private PostRepo postRepo;
	private UserRepo userRepo;
	
	@Override
	public Post createNewPost(Post post, Long userId) throws Exception {
		User user = userRepo.findById(userId).orElseThrow(() -> new Exception("User not found!!!"));
		
		Post newPost = Post.builder()
							.caption(post.getCaption())
							.image(post.getImage())
							.video(post.getVideo())
							.user(user)
							.createdOn(LocalDateTime.now())
							.build();
		Post fetchedPost = postRepo.save(newPost);
		
		return fetchedPost;
	}

	@Override
	public String deletePost(Long postId, Long userId) throws Exception {
		
		Post post = postRepo.findById(postId).orElseThrow(() -> new Exception("Post not found!!!"));
		User user = userRepo.findById(userId).orElseThrow(() -> new Exception("User not found!!!"));
		
		if(post.getUser().getId() != user.getId())
			throw new Exception("This Post Doesnt belongs to you");
		
		postRepo.delete(post);
		
		return "Post deleted successfully!!!";
	}

	@Override
	public List<Post> findPostByUserId(Long userId) {
		
		List<Post> posts = postRepo.findPostByUserId(userId);
		
		return posts;
	}

	@Override
	public Post findPostById(Long postId) throws Exception {
		Post post = postRepo.findById(postId).orElseThrow(() -> new Exception("Post not found!!!"));
		
		return post;
	}

	@Override
	public List<Post> getAllPost() {
		
		return postRepo.findAll();
	}

	@Override
	public Post savedPost(Long postId, Long userId) throws Exception {
		
		Post post = postRepo.findById(postId).orElseThrow(() -> new Exception("Post not found!!!"));
		User user = userRepo.findById(userId).orElseThrow(() -> new Exception("User not found!!!"));
		
		if(user.getSavedPost().contains(post))
			user.getSavedPost().remove(post);
		else
			user.getSavedPost().add(post);
		
		userRepo.save(user);
		return post;
	}

	@Override
	public Post likedPost(Long postId, Long userId) throws Exception {
		
		Post post = postRepo.findById(postId).orElseThrow(() -> new Exception("Post not found!!!"));
		User user = userRepo.findById(userId).orElseThrow(() -> new Exception("User not found!!!"));
		
		if(post.getLiked().contains(user))
			post.getLiked().remove(user);
		else
			post.getLiked().add(user);
		
		return postRepo.save(post);
	}

}
