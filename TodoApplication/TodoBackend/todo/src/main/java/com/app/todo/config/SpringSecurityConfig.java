package com.app.todo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import lombok.AllArgsConstructor;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SpringSecurityConfig {
	
	private UserDetailsService userDetailsService;
	
	@Bean
	static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf((csrf) -> csrf.disable())
			.authorizeHttpRequests((authz) ->{
//						authz.requestMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN");
//						authz.requestMatchers(HttpMethod.PUT, "/api/**").hasRole("ADMIN");
//						authz.requestMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN");
//						authz.requestMatchers(HttpMethod.GET, "/api/**").hasAnyRole("ADMIN", "USER");
//						authz.requestMatchers(HttpMethod.PATCH, "/api/**").hasAnyRole("ADMIN", "USER");
// For .permitAll() we do not need any Role its accessible to everyone doesn't require any sign it to access this url
//						authz.requestMatchers(HttpMethod.GET, "/api/**").permitAll();
					authz.requestMatchers("/api/auth/**").permitAll();
					authz.anyRequest().authenticated();
			}).httpBasic(Customizer.withDefaults());
		
		return http.build();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

//    @Bean
//    UserDetailsService userDetailsService() {
//		
//		UserDetails yash = User.builder()
//								.username("yash")
//								.password(passwordEncoder().encode( "password"))
//								.roles("USER")
//								.build();
//		UserDetails admin = User.builder()
//								.username("admin")
//								.password(passwordEncoder().encode( "admin"))
//								.roles("ADMIN")
//								.build();
//		return new InMemoryUserDetailsManager(yash, admin);
//	}
}
