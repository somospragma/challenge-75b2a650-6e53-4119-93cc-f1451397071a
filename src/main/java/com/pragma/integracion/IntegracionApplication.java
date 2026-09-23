package com.pragma.integracion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import reactor.core.publisher.Hooks;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

@SpringBootApplication
@EnableAsync
public class IntegracionApplication {

    public static void main(String[] args) {
        Hooks.onOperatorDebug();
        SpringApplication.run(IntegracionApplication.class, args);
    }

    @Bean
    public MeterRegistry meterRegistry() {
        return new SimpleMeterRegistry();
    }

    @Bean
    public void configureResilience() {
        // Configuración global de Resilience4j para circuit breaker y retry
        io.github.resilience4j.circuitbreaker.CircuitBreakerConfig circuitBreakerConfig =
            io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(java.time.Duration.ofMillis(1000))
                .permittedNumberOfCallsInHalfOpenState(3)
                .slidingWindowSize(10)
                .recordExceptions(java.io.IOException.class, org.springframework.kafka.KafkaException.class)
                .build();

        io.github.resilience4j.retry.RetryConfig retryConfig =
            io.github.resilience4j.retry.RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(java.time.Duration.ofMillis(500))
                .retryExceptions(java.io.IOException.class, org.springframework.kafka.KafkaException.class)
                .build();

        // Registrar configuraciones globales
        io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry.of(circuitBreakerConfig);
        io.github.resilience4j.retry.RetryRegistry.of(retryConfig);
    }
}