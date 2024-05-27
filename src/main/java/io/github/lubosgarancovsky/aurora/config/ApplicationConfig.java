package io.github.lubosgarancovsky.aurora.config;

import java.util.UUID;

import io.github.lubosgarancovsky.aurora.domain.user.query.users.FindUserByIdQuery;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.github.lubosgarancovsky.aurora.business.user.repository.JooqUserRepository;
import io.github.lubosgarancovsky.aurora.domain.user.entity.UserEntity;
import io.github.lubosgarancovsky.aurora.domain.user.query.users.ImmutableFindUserByIdQuery;

@Configuration
public class ApplicationConfig {

    private final JooqUserRepository jooqUserRepository;
  
    public ApplicationConfig(JooqUserRepository jooqUserRepository) {
        this.jooqUserRepository = jooqUserRepository;
    }  

    @Bean  
    public UserDetailsService userDetailsService() {  
	    return id -> {
            UserEntity user = jooqUserRepository.findOne(query(id));

            return User.builder()
                .username(user.email())
                .password(user.password())
                .build();
        };  
    }  

    @Bean  
    public AuthenticationProvider authenticationProvider() {  
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();  
        provider.setUserDetailsService(userDetailsService());  
        provider.setPasswordEncoder(passwordEncoder());  
        return provider;  
    }  


    @Bean  
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {  
	    return config.getAuthenticationManager();  
    } 

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private FindUserByIdQuery query(String id) {
        return ImmutableFindUserByIdQuery.builder()
            .id(UUID.fromString(id))
            .build();
    }
}
