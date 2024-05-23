package com.spring_one.webSerrver.security.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    /**
     * An AuthenticationService dependency injected via @RequiredArgsConstructor.
     */
    private final AuthenticationService authenticationService;


    /**
     *
     *
     * @param request - The @RequestBody annotation is used to deserialize the HTTP request into a Java object.
     * The object's class (RegisterRequest) must contain the fields that the JSON request body would have.
     *
     * @return Response Entity is a generic class representing an HTTP response.
     * It serialises the data from the AuthenticationResponse object such that it can be returned as JSON.
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register (
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    /**
     *
     *
     * @param request - The @RequestBody annotation is used to deserialize the HTTP request into a Java object.
     * The object's class (RegisterRequest) must contain the fields that the JSON request body would have.
     *
     * @return Response Entity is a generic class representing an HTTP response.
     * It serialises the data from the AuthenticationResponse object such that it can be returned as JSON.
     */
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticateRequest (
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

}
