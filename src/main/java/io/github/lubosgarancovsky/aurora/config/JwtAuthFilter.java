package io.github.lubosgarancovsky.aurora.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import io.github.lubosgarancovsky.aurora.domain.user.query.users.FindUserByIdQuery;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.github.lubosgarancovsky.aurora.business.user.service.FindUserByIdService;
import io.github.lubosgarancovsky.aurora.business.user.service.JwtService;
import io.github.lubosgarancovsky.aurora.domain.user.entity.UserEntity;
import io.github.lubosgarancovsky.aurora.domain.user.query.users.ImmutableFindUserByIdQuery;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final FindUserByIdService findUserByIdService;

    public JwtAuthFilter(JwtService jwtService, FindUserByIdService findUserByIdService) {
        this.jwtService = jwtService;
        this.findUserByIdService = findUserByIdService;
    }

    	  
	@Override  
	protected void doFilterInternal(  
        HttpServletRequest request,  
        HttpServletResponse response,  
        FilterChain filterChain) throws ServletException, IOException {  
        
        
        final String authHeader = request.getHeader("Authorization");  
        
        if(authHeader == null || !authHeader.startsWith("Bearer ")) {  
            filterChain.doFilter(request, response);  
            return;  
        }  
        
        final String jwt = authHeader.substring(7);  
        final String subject = jwtService.extractSubject(jwt);  
        
        if(subject != null && SecurityContextHolder.getContext().getAuthentication() == null) { 
            FindUserByIdQuery query = ImmutableFindUserByIdQuery.builder()
                                                    .id(UUID.fromString(subject))
                                                    .build(); 

            UserEntity user = this.findUserByIdService.execute(query);  
        
        if(jwtService.isTokenValid(jwt, user)) {  
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(  
            user, null, List.of(new SimpleGrantedAuthority("USER"))
        );  
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));  
            SecurityContextHolder.getContext().setAuthentication(authToken);  
        }  
        }  
        filterChain.doFilter(request, response);  
	}  
    
}
