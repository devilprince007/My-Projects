package com.life.social.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Post {
	@Id
	@GeneratedValue(generator = "post_sequence",strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "post_sequence", sequenceName = "post_seq", initialValue = 00000)
	private Long id;
	
	private String caption;
	
	private String image;
	
	private String video;
	
	@ManyToOne
	private User user;
	
	@Builder.Default
	@OneToMany
	private List<User> liked = new ArrayList<>();

	@Column(nullable = false)
	private LocalDateTime createdOn;

}
