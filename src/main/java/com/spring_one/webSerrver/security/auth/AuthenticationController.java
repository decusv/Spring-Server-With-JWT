package com.spring_one.webSerrver.security.auth;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Authentication endpoints")
public class AuthenticationController {

    /**
     * An AuthenticationService dependency injected via @RequiredArgsConstructor.
     */
    private final AuthenticationService authenticationService;


    /**
     * @param request - The @RequestBody annotation is used to deserialize the HTTP request into a Java object.
     *                The object's class (RegisterRequest) must contain the fields that the JSON request body would have.
     * @return Response Entity is a generic class representing an HTTP response.
     * It serialises the data from the AuthenticationResponse object such that it can be returned as JSON.
     */

    @SuppressWarnings("unused")
    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Register a new user with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully",
                    content = @Content(schema = @Schema(implementation = AuthenticationResponseWrapper.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body")
    })
    public ResponseEntity<AuthenticationResponseWrapper> register (
            @RequestBody RegisterRequest request
    ) {
       AuthenticationResponse response = authenticationService.register(request);
       AuthenticationResponseWrapper responseWrapper = new AuthenticationResponseWrapper(response);
       return ResponseEntity.ok(responseWrapper);
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
    @SuppressWarnings("unused")
    @PostMapping("/authenticate")
    @Operation(summary = "Authenticate a user", description = "Authenticate a user with the provided credentials")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User authenticated successfully",
                    content = @Content(schema = @Schema(implementation = AuthenticationResponseWrapper.class))),
            @ApiResponse(responseCode = "403", description = "Invalid credentials")
    })
    public ResponseEntity<AuthenticationResponseWrapper> authenticateRequest (
            @RequestBody AuthenticationRequest request
    ) {
        AuthenticationResponse response = authenticationService.authenticate(request);
        AuthenticationResponseWrapper responseWrapper = new AuthenticationResponseWrapper(response);
        return ResponseEntity.ok(responseWrapper);
    }

}
