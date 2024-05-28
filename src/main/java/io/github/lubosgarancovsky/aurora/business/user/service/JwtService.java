package io.github.lubosgarancovsky.aurora.business.user.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.github.lubosgarancovsky.aurora.domain.user.entity.UserEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtService {
    
    private final String SECRET = "AwmEKuBjGAMorU_^eyA)SAD6*DdRW.8*m'kMBu=AQdcS8`K|kTDvzr*0)fVlTp~";
    private final Integer EXPIRATION_MINS = 120;


    public String extractSubject(String token) {  
		return extractClaim(token, Claims::getSubject);  
	}  
	  
	public String extractSubject(HttpServletRequest request) {  
		final String authHeader = request.getHeader("Authorization");  
		return this.extractSubject(authHeader.substring(7));  
	}  

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) { 
		final Claims claims = extractAllClaims(token);  
		return claimsResolver.apply(claims);  
	}  
	  
	public String generateToken(UserEntity user) {  
		Map<String, Object> claims = this.getUserClaims(user);  
		return generateToken(user, claims);  
	}  

    public String generateToken(UserEntity user, Map<String, Object> claims) {  
		return Jwts  
		.builder()  
		.claims(claims)  
		.subject(user.id().toString())  
		.issuedAt(new Date(System.currentTimeMillis()))  
		.expiration(new Date(System.currentTimeMillis() + this.EXPIRATION_MINS * 1000L * 60))  
		.signWith(this.getSigningKey())  
		.compact();  
	}  
	  
	public boolean isTokenValid(String token, UserEntity user) {  
		final String subject = extractSubject(token);  
		return (subject.equals(user.id().toString()) && !isTokenExpired(token));  
	}  
	  
	public boolean isTokenExpired(String token) {  
		return this.extractClaim(token, Claims::getExpiration).before(new Date());  
	} 
	  
	private Map<String, Object> getUserClaims(UserEntity user) {  
		Map<String, Object> claims = new HashMap<>();  
		claims.put("name", user.name());
		claims.put("email", user.email());
		claims.put("color", user.color());
		claims.put("picture", user.picture());
		claims.put("createdAt", user.createdAt().toString());
		claims.put("updatedAt", user.updatedAt().toString());
		return claims;  
	}  
	  
	  
	private Claims extractAllClaims(String token) {  
		return Jwts  
		.parser()  
		.verifyWith((SecretKey) this.getSigningKey())  
		.build()  
		.parseSignedClaims(token)  
		.getPayload();  
	}  
	  
	private Key getSigningKey() {  
		byte[] keyBytes = this.SECRET.getBytes();  
		return Keys.hmacShaKeyFor(keyBytes);  
	} 

}
