package com.spring_one.webSerrver.security.config;

import com.spring_one.webSerrver.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


// TODO: Why do we need a RequiredArgsConstructor? (In case we want to inject something)
@RequiredArgsConstructor
@Configuration
public class ApplicationConfig
{

    private final UserRepository userRepository;

    // TODO: What does this implementation do?
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            return userRepository.findUserByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found."));
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        // Data Access object (Dao) responsible to fetch user details and encode passwords.
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        // specify properties of the authProvider i.e. how to fetch the correct userDetails service to fetch information about the user.
        // You may have multiple userDetails service implementation based on where the user data may be retrieved from.
        authProvider.setUserDetailsService(userDetailsService());
        // TODO : What is a password encoder?
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    // TODO: What is Authentication Manager responsible for?
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        // TODO : What is a BCryptPassword encoder?
        return new BCryptPasswordEncoder();
    }
}
