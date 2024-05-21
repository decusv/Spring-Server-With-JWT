    package com.spring_one.webSerrver.security.config.filters;

    import io.github.bucket4j.Bandwidth;
    import io.github.bucket4j.Bucket;
    import jakarta.servlet.Filter;
    import jakarta.servlet.FilterChain;
    import jakarta.servlet.FilterConfig;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.ServletRequest;
    import jakarta.servlet.ServletResponse;
    import jakarta.servlet.http.HttpServletResponse;
    import org.springframework.stereotype.Component;

    import java.io.IOException;
    import java.time.Duration;


    @Component
    public class RateLimitFilter implements Filter {

        private Bucket bucket;

        @Override
        public void init(FilterConfig filterConfig) {
            Bandwidth limit = Bandwidth.builder().capacity(10).refillGreedy(10, Duration.ofMinutes(1)).build();
            bucket = Bucket.builder().addLimit(limit).build();
        }

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            if (bucket.tryConsume(1)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
                String errorMessage = "{\"error\": \"Rate limit exceeded\"}";
                httpResponse.setContentType("application/json");
                httpResponse.getWriter().write(errorMessage);
            }
        }

        @Override
        public void destroy() {

        }

        // other methods
    }
