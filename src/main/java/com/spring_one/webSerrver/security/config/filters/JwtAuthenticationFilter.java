package com.spring_one.webSerrver.security.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// We define this class as @Component, so it is detected and registered by Spring.
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // TODO: Why is the final classifier keyword used?
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(

            @NonNull HttpServletRequest request, // Incoming HTTP request. Allows us to extract date from the request.
            @NonNull HttpServletResponse response, // Allows us to modify the HTTP response back e.g., add additional headers.
            @NonNull FilterChain filterChain // Chain of responsibility design pattern.
    )
            throws ServletException, IOException {
                final String authHeader = request.getHeader("Authorization"); // Contains the auth token.
                final String jwt;
                final String userEmail;
                if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                    // Filter chain is an ordered list of filters, where the order of filters can also be determined if needed by custom order or priority.
                    filterChain.doFilter(request,response);
                    return;
                }
                jwt = authHeader.substring(7); // Extract token after "Bearer" keyword.

                userEmail = jwtService.extractEmail(jwt); // Extract email from the JWT token.

                // Checks if the client is already authenticated.
                // Lack of email/username provided in the JWT means we can't proceed with validating the user.
                // if getAuthentication returns null, that means user isn't authenticated.
                if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                    UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
                    if (jwtService.isTokenValid(jwt,userDetails)) {
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities()
                        );
                        // Extend/Enforce the authentication token with the details of the incoming HTTP request.
                        authToken.setDetails(
                                new WebAuthenticationDetailsSource().buildDetails(request)
                        );
                        SecurityContextHolder.getContext().setAuthentication((authToken));
                    }
                }
                // Pass the request and response onto the next step of the filter chain.
                filterChain.doFilter(request,response);

    }
}
