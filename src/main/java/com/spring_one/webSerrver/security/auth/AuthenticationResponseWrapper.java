package com.spring_one.webSerrver.security.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/*
  AuthenticationResponseWrapper is a DTO used to wrap AuthenticationResponse and provide better structure.
  It does so by providing a parent object when the data is serialized to JSON.
 */
public class AuthenticationResponseWrapper {


    private AuthenticationResponse authentication;
    private metadataResponse meta_data;

    public AuthenticationResponseWrapper(AuthenticationResponse data) {
        this.authentication = data;
        this.meta_data = new metadataResponse();
    }

}
