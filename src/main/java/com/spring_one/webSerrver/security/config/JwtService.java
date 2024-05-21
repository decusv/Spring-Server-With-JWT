package com.spring_one.demo.security.user.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

// Annotations are needed for the class to be detected and initialized as a Spring Bean.
@Service
public class JwtService {

    private static final String SECRET_KEY = "1RBG5HMjuRAYfM88b2UdESVW0iyFZ5cY+ibhMctd9oD5ASyfziAsJ5XHA7A5FIe2fghDN51W7SUZrr8sATkV7Hf6+76X+/BEF3F/s+Gg2bV1th7/UC77Ho688rQ1ZiJEvTxkN6jELmiiAwvM5H0O2vDK1sx313n6nPkHtUDRzstxtyAdTuLMnMo+pT0pvKnrRWAXKvTVrfcjB3n4O7PbeM3J4SBJ8dJfsMCDrKvW6zQGILGfrGKUJUiGi+hUXiRUtpSJqAL5ysmufInTCcZEO5EOlSegECNoKWpJ50axQj/eoBWM3890FScYcyH5CAO49Mw48fMrUQz6hANPM9/VOnqYRNghvX//j3++2o619GQ=";

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSignInKey() {

        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        // Given the SECRET_KEY is 256 bit, algorithm is implied to be "HmacSHA256" and in turn 'HS256'.
        return Keys.hmacShaKeyFor(keyBytes);


    }

    // TODO : What is this method signature syntax?
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // TODO : What is this Claims::getSubject syntax?
    public String extractEmail(String token) {
        return extractClaim(token,Claims::getSubject);
    }

    // Validate that the username extracted from the token is the same username provided in the JWT payload.
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractEmail(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);

    }

    // TODO: How are we comparing dates here?
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Method extracts the 'exp' claim from the JWT token.
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    // TODO: Why do we have to generate JWT token of our own?
    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24 ))
                .signWith(getSignInKey()) // Signing Algorithm is assumed. Using
                .compact();
    }

}
