package com.spring_one.webSerrver.security.config;


import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;

@Component
public class RateLimitFilter extends OncePerRequestFilter implements Ordered {

    private final Bucket bucket;

    public RateLimitFilter() {
        Bandwidth limit = Bandwidth.builder().capacity(10).refillGreedy(10,Duration.ofMinutes(1)).build();
        this.bucket = Bucket.builder().addLimit(limit).build();
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull jakarta.servlet.http.HttpServletResponse response,@NonNull jakarta.servlet.FilterChain filterChain) throws jakarta.servlet.ServletException, IOException {
        if (bucket.tryConsume(1)) {
            filterChain.doFilter(request, response);
        } else {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setStatus(HttpStatus.TOO_MANY_REQUESTS.value() );
            httpResponse.getWriter().write("Too many requests");
        }
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

}
