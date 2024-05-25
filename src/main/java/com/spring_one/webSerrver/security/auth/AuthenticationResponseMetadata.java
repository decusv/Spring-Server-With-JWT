package com.spring_one.webSerrver.security.auth;

import lombok.*;

import java.time.Instant;


/**
 * metadataResponse is an object used to specifying metadata inside authentication response.
 */
@Getter
@Data
@Builder
@AllArgsConstructor
public class AuthenticationResponseMetadata {

    private Instant utc_timestamp;

    public AuthenticationResponseMetadata() {
        this.utc_timestamp = Instant.now();
    }

}
