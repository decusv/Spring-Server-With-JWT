package com.spring_one.webSerrver.openApi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SuppressWarnings("unused")
public class OpenApiConfig {
    @Bean
    @SuppressWarnings("unused")
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MetaQ API Reference")
                        .version("1.0")
                        .description("Your API Description")
                        .description("Your External Documentation Description"));
    }
}