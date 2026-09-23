package com.pragma.integracion.infrastructure.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import io.github.resilience4j.reactor.retry.operator.RetryOperator;
import io.github.resilience4j.retry.RetryConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class ResilienceConfig {
    @Bean
    public CircuitBreakerOperator circuitBreakerOperator() {
        return CircuitBreakerOperator.of("event-publisher-circuit-breaker",
            CircuitBreakerConfig.custom().build());
    }

    @Bean
    public RetryOperator retryOperator() {
        return RetryOperator.of(RetryConfig.custom().maxAttempts(3).build());
    }

    @Bean
    public Mono<String> fallbackMethod() {
        return Mono.just("Fallback response");
    }
}