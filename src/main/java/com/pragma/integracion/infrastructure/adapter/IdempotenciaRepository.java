package com.pragma.integracion.infrastructure.adapter;

import com.pragma.integracion.domain.port.IdempotenciaRepositoryPort;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class IdempotenciaRepository implements IdempotenciaRepositoryPort {

    private final ReactiveRedisTemplate<String, String> redisTemplate;

    public IdempotenciaRepository(ReactiveRedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Mono<Boolean> existeClave(String claveIdempotencia) {
        return redisTemplate.hasKey(claveIdempotencia);
    }

    @Override
    public Mono<Void> guardarClave(String claveIdempotencia) {
        return redisTemplate.opsForValue().set(claveIdempotencia, "true");
    }

    @Override
    public Mono<Void> eliminarClave(String claveIdempotencia) {
        return redisTemplate.delete(claveIdempotencia);
    }
}