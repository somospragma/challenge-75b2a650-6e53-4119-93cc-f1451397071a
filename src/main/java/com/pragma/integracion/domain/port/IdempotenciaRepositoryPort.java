package com.pragma.integracion.domain.port;

import reactor.core.publisher.Mono;

/**
 * Puerto que define el contrato para verificar y almacenar claves de idempotencia.
 * Implementaciones concretas deben garantizar consistencia y durabilidad.
 */
public interface IdempotenciaRepositoryPort {

    /**
     * Verifica si una clave de idempotencia ya existe en el repositorio.
     * @param claveIdempotencia Clave de idempotencia a verificar
     * @return Mono<Boolean> que emite true si la clave existe, false en caso contrario
     */
    Mono<Boolean> existeClave(String claveIdempotencia);

    /**
     * Almacena una clave de idempotencia en el repositorio.
     * @param claveIdempotencia Clave de idempotencia a almacenar
     * @return Mono<Void> que completa cuando la clave es almacenada exitosamente
     */
    Mono<Void> guardarClave(String claveIdempotencia);

    /**
     * Elimina una clave de idempotencia del repositorio.
     * @param claveIdempotencia Clave de idempotencia a eliminar
     * @return Mono<Void> que completa cuando la clave es eliminada exitosamente
     */
    Mono<Void> eliminarClave(String claveIdempotencia);
}