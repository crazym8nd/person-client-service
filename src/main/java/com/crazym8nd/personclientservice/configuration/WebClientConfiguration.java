package com.crazym8nd.personclientservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {
    @Bean
    public WebClient webClient() {
        String baseUrl = System.getenv("INDIVIDUALS_API_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            throw new IllegalStateException("INDIVIDUALS_API_URL environment variable is not set");
        }
        return WebClient.create(baseUrl);
    }
}
