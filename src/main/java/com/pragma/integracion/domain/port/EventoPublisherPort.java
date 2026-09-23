package com.pragma.integracion.domain.port;

import com.pragma.integracion.domain.model.EventoTransaccional;
import reactor.core.publisher.Mono;

/**
 * Puerto que define el contrato para publicar eventos en el bus de eventos.
 * Implementaciones concretas deben garantizar el envío idempotente y resiliente.
 */
public interface EventoPublisherPort {

    /**
     * Publica un evento transaccional en el bus de eventos.
     * @param evento Evento transaccional a publicar
     * @return Mono<Void> que completa cuando el evento es publicado exitosamente,
     *         o falla con una excepción si ocurre un error irrecuperable
     */
    Mono<Void> publicarEvento(EventoTransaccional evento);

    /**
     * Verifica si un evento con la misma clave de idempotencia ya fue publicado.
     * @param claveIdempotencia Clave de idempotencia a verificar
     * @return Mono<Boolean> que emite true si el evento ya fue publicado, false en caso contrario
     */
    Mono<Boolean> existeEvento(String claveIdempotencia);
}