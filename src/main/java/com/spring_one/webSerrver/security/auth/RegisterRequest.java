package com.spring_one.webSerrver.security.auth;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
* Generates getters, setters and toString methods.
*/
@Data
/*
 * Generates a builder pattern that allows for RegisterRequest instances to be constructed.
 * Improves readability in constructing the object.
 * Allows for optional attribute handling.
 * Allows for object immutability (object's state cannot be changed post creation).
 * Allows you to validate the construction of the object is correct e.g., required fields are populated.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
/*
 * The RegisterRequest class serves as a data container for holding user registration information.
 * These are deserialized from the incoming HTTP request within the AuthenticationController Java class.
 */
public class RegisterRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
}