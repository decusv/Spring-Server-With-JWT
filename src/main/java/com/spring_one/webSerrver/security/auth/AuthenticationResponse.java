package com.spring_one.webSerrver.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

/*
 * AuthenticationResponse is a Data Transfer Object (DTO). Its purpose is to hold token information that will be
 * serialized into JSON such that it can be stored inside an HTTP response and sent back to the client.
 */
public class AuthenticationResponse {

    private String token;



}
