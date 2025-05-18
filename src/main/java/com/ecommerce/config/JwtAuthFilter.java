package com.ecommerce.config;

import com.ecommerce.utils.JwtUtils;
import com.ecommerce.service.impl.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    private static final List<String> WHITELIST = List.of(
    	    "/auth",
    	    "/v2/api-docs",
    	    "/v3/api-docs",
    	    "/swagger-ui",
    	    "/swagger-ui.html",
    	    "/swagger-resources",
    	    "/webjars",
    	    "/favicon.ico",
    	    "/productsAll"
    	);

    	private boolean isWhitelistedPath(String path) {
    	    return WHITELIST.stream().anyMatch(path::startsWith);
    	}
    	

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
                                    throws ServletException, IOException {
    	
    	String path = request.getRequestURI();
        if (isWhitelistedPath(path)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = authHeader.substring(7);
        final String username = jwtUtils.getUsernameFromToken(token);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (jwtUtils.validateToken(token)) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
