package com.pragma.integracion.application;

import com.pragma.integracion.domain.model.EventoTransaccional;
import com.pragma.integracion.domain.port.EventoPublisherPort;
import com.pragma.integracion.domain.port.IdempotenciaRepositoryPort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class EventoOrquestador {

    private final EventoPublisherPort eventoPublisher;
    private final IdempotenciaRepositoryPort idempotenciaRepository;

    public EventoOrquestador(EventoPublisherPort eventoPublisher, IdempotenciaRepositoryPort idempotenciaRepository) {
        this.eventoPublisher = eventoPublisher;
        this.idempotenciaRepository = idempotenciaRepository;
    }

    public Mono<Void> orquestarEvento(EventoTransaccional evento) {
        return idempotenciaRepository.existeClave(evento.generarClaveIdempotencia())
           .flatMap(existe -> {
                if (existe) {
                    return Mono.error(new IdempotenciaException("Evento ya procesado"));
                }
                return eventoPublisher.publicarEvento(evento)
                   .then(idempotenciaRepository.guardarClave(evento.generarClaveIdempotencia()));
            });
    }
}