package com.ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private JwtAuthFilter jwtAuthFilter;
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth
	        	.requestMatchers("/auth/**",
	                    "/v3/api-docs/**",
	                    "/v2/api-docs/**",
	                    "/swagger-ui/**",
	                    "/swagger-ui.html",
	                    "/swagger-resources/**",
	                    "/webjars/**","/productsAll").permitAll()
	            .requestMatchers("/user/**").hasRole("USER")
	            .requestMatchers("/admin/**").hasRole("ADMIN")
	            .anyRequest().authenticated()
	        ).exceptionHandling(ex -> ex
	                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
	        )
	        .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); // register here

	    return http.build();
	}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
