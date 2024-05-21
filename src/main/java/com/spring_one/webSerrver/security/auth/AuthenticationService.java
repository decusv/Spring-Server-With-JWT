package com.spring_one.webSerrver.security.auth;

import com.spring_one.webSerrver.security.config.JwtService;
import com.spring_one.webSerrver.security.user.Role;
import com.spring_one.webSerrver.security.user.User;
import com.spring_one.webSerrver.security.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/*
  Service is a Spring stereotype of a @Component annotation used for defining business logic.
 */
@Service
/*
  Defines a class constructor for every 'final' field defined in the class, thus carrying out a dependency injection via a constructor injection.
 */
@AllArgsConstructor
public class AuthenticationService {


    /*
      "Final" keyword
      Ensures immutability; this means the class doesn't get unpredictable changed by something else and therefore helps to prevent bugs.
      Ensures thread-safety; different threads can safely read a field in the object which ensures that another thread hasn't modified the field.
     */

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /*
     Method takes in RegisterRequest in order to generate an HTTP response from the auth service.
     */
    public AuthenticationResponse register(RegisterRequest request) {
        /*
         User class has a @Builder Lombok annotation to create a Builder pattern. We assign values to the instance of User below.
         */
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        /*
            Method "save" is provided by the JpaRepository interface that is implemented. The instance "user" is either created or updated within the connected DB.
         */
        userRepository.save(user);
        // TODO : why can we pass user variable and not UserDetails?
        var jwtToken = jwtService.generateToken(user);
        // TODO: What does this syntax work like?
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );

        // If this point is reached, then the user is authenticated i.e., the username and password are correct.
        // All we need to do is generate a JWT token and send it back.
        // TODO : Why are we sending back a JWT token? I

        var user = userRepository.findUserByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
