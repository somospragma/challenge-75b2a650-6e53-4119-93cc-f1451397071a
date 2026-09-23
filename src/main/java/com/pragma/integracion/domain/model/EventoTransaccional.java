package com.pragma.integracion.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Modelo canónico del evento transaccional que representa una operación bancaria.
 * Contiene los campos de negocio y la clave de idempotencia para garantizar
 * que el evento no sea procesado duplicadamente.
 */
public record EventoTransaccional(
    UUID idEvento,
    String claveIdempotencia,
    String tipoEvento,
    String numeroCuentaOrigen,
    String numeroCuentaDestino,
    BigDecimal monto,
    String moneda,
    LocalDateTime fechaTransaccion,
    String estado,
    String codigoOficina,
    String canal,
    String referencia,
    String usuarioCreador,
    LocalDateTime timestampCreacion,
    Integer intentosEnvio,
    String traceId
) {
    /**
     * Constructor que genera una clave de idempotencia basada en los campos clave de negocio.
     * @param tipoEvento Tipo de evento (ej: TRANSFERENCIA, DEPOSITO)
     * @param numeroCuentaOrigen Número de cuenta origen
     * @param numeroCuentaDestino Número de cuenta destino (null para eventos que no aplican)
     * @param monto Monto de la transacción
     * @param referencia Referencia única de negocio
     * @param timestampCreacion Timestamp de creación del evento
     * @return Clave de idempotencia generada
     */
    public static String generarClaveIdempotencia(String tipoEvento, String numeroCuentaOrigen,
                                                 String numeroCuentaDestino, BigDecimal monto,
                                                 String referencia, LocalDateTime timestampCreacion) {
        StringBuilder sb = new StringBuilder();
        sb.append(tipoEvento).append("|");
        sb.append(numeroCuentaOrigen).append("|");
        if (numeroCuentaDestino != null) {
            sb.append(numeroCuentaDestino).append("|");
        }
        sb.append(monto.toPlainString()).append("|");
        sb.append(referencia).append("|");
        sb.append(timestampCreacion.toString());
        return sb.toString();
    }

    /**
     * Valida que el evento contiene los campos requeridos para ser procesado.
     * @throws IllegalArgumentException si algún campo requerido es nulo o vacío
     */
    public void validar() {
        if (idEvento == null) {
            throw new IllegalArgumentException("El idEvento no puede ser nulo");
        }
        if (claveIdempotencia == null || claveIdempotencia.isBlank()) {
            throw new IllegalArgumentException("La claveIdempotencia no puede ser nula o vacía");
        }
        if (tipoEvento == null || tipoEvento.isBlank()) {
            throw new IllegalArgumentException("El tipoEvento no puede ser nulo o vacío");
        }
        if (numeroCuentaOrigen == null || numeroCuentaOrigen.isBlank()) {
            throw new IllegalArgumentException("El numeroCuentaOrigen no puede ser nulo o vacío");
        }
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser positivo");
        }
        if (moneda == null || moneda.isBlank()) {
            throw new IllegalArgumentException("La moneda no puede ser nula o vacía");
        }
        if (fechaTransaccion == null) {
            throw new IllegalArgumentException("La fechaTransaccion no puede ser nula");
        }
        if (estado == null || estado.isBlank()) {
            throw new IllegalArgumentException("El estado no puede ser nulo o vacío");
        }
        if (canal == null || canal.isBlank()) {
            throw new IllegalArgumentException("El canal no puede ser nulo o vacío");
        }
        if (traceId == null || traceId.isBlank()) {
            throw new IllegalArgumentException("El traceId no puede ser nulo o vacío");
        }
    }

    /**
     * Crea una nueva instancia del evento con el contador de intentos incrementado.
     * @return Nueva instancia de EventoTransaccional con intentosEnvio incrementado
     */
    public EventoTransaccional incrementarIntentosEnvio() {
        return new EventoTransaccional(
            this.idEvento,
            this.claveIdempotencia,
            this.tipoEvento,
            this.numeroCuentaOrigen,
            this.numeroCuentaDestino,
            this.monto,
            this.moneda,
            this.fechaTransaccion,
            this.estado,
            this.codigoOficina,
            this.canal,
            this.referencia,
            this.usuarioCreador,
            this.timestampCreacion,
            this.intentosEnvio != null ? this.intentosEnvio + 1 : 1,
            this.traceId
        );
    }
}