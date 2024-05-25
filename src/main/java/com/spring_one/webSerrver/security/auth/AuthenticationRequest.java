package com.spring_one.webSerrver.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Data generates getters and setters.
 * Builder generates a builder pattern class for this class which makes object assembly easier and less error-prone.
 * AllArgsConstructor generates a constructor that accepts all args and therefore allows for object creation.
 * NoArgsConstructor is required for JPA when used in deserialising HTTP requests into Java objects.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    private String email;
    String password;
}
