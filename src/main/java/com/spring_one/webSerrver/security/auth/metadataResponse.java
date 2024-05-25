package com.spring_one.webSerrver.security.auth;

import lombok.*;

import java.time.Instant;


@Getter
@Data
@Builder
@AllArgsConstructor

public class metadataResponse {

    private Instant utc_timestamp;

    public metadataResponse() {
        this.utc_timestamp = Instant.now();
    }

}
