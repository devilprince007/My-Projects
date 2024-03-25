package com.life.social.Model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {
	@Id
	@GeneratedValue(generator = "user_sequence",strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "user_sequence", sequenceName = "user_seq", initialValue = 10000)
	private Long id;
	
	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;
	
	@Column(nullable = false)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String gender;

	@Builder.Default
	private List<Long> followers = new ArrayList<>();
	
	@Builder.Default
	private List<Long> followings = new ArrayList<>();
	
	@Builder.Default
	@ManyToMany
	@JsonIgnore
	private List<Post> savedPost = new ArrayList<>();
	
}
