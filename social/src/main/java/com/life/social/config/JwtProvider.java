package com.life.social.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtProvider {
	private static SecretKey key = Keys.hmacShaKeyFor(JwtConstants.SECRET_KEY.getBytes());
	
	public static String generateToken(Authentication auth) {
		
		@SuppressWarnings("deprecation")
		String jwt = Jwts.builder().setIssuer("CodeWithYash")
								.setIssuedAt(new Date())
								.setExpiration(new Date(new Date().getTime() + 86400000))
								.claim("email", auth.getName())
								.signWith(key)
								.compact();
		
		return jwt;
	}
	
	public static String getEmailFromJwtToken(String jwtToken) {
//		Bearer token...
		jwtToken = jwtToken.substring(7);

		Claims claims = Jwts.parser()
		        			.verifyWith(key)
		        			.build()
		        			.parseSignedClaims(jwtToken)
		        			.getPayload();
		
		if(claims == null) {
			System.out.println("Claims is null!!!");
		}
		String email = String.valueOf(claims.get("email"));
		
		return email;
	}
}
