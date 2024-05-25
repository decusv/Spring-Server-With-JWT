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

    /**
     * Method creates a user object with provided details inside the HTTP request, saves the user and generates
     * initial JWT token. Then returns a builder object for AuthenticationResponse with initial JWT token that was generated.
     * @param request - Incoming HTTP request.
     * @return an {@code AuthenticationResponse} object containing a JWT token if authentication is successful.
     */
    public AuthenticationResponse register(RegisterRequest request) {
        /*
         User class has a @Builder Lombok annotation to create a Builder pattern. We assign values to the instance of User below.
         */
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // Hashes and salts the password so that the password can be verified but not reversed.
                .role(Role.USER)
                .build();

        /*
            Method "save" is provided by the JpaRepository interface that is implemented. The instance "user" is either created or updated within the connected DB.
         */
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    /**
     * Method attempts to verify whether the provided username and password are what is stored in the database.
     * @param request the incoming HTTP request containing the authentication details.
     * @return an {@code AuthenticationResponse} object containing a JWT token if authentication is successful.
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );

        // If this point is reached, then the user is authenticated i.e., the username and password are correct.
        // All we need to do is generate a JWT token and send it back.

        var user = userRepository.findUserByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
