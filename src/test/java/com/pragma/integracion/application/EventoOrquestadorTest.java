package com.pragma.integracion.application;

import com.pragma.integracion.domain.model.EventoTransaccional;
import com.pragma.integracion.domain.port.EventoPublisherPort;
import com.pragma.integracion.domain.port.IdempotenciaRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EventoOrquestadorTest {

    @Mock
    private EventoPublisherPort eventoPublisherPort;

    @Mock
    private IdempotenciaRepositoryPort idempotenciaRepositoryPort;

    @InjectMocks
    private EventoOrquestador eventoOrquestador;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testIdempotencia() {
        EventoTransaccional evento = new EventoTransaccional("tipo", "cuenta");
        when(idempotenciaRepositoryPort.existeClave(evento.generarClaveIdempotencia())).thenReturn(Mono.just(true));
        when(eventoPublisherPort.publicarEvento(evento)).thenReturn(Mono.empty());
        eventoOrquestador.publicarEvento(evento);
        verify(eventoPublisherPort, times(1)).publicarEvento(evento);
    }

    @Test
    void testManejoFallas() {
        EventoTransaccional evento = new EventoTransaccional("tipo", "cuenta");
        when(idempotenciaRepositoryPort.existeClave(evento.generarClaveIdempotencia())).thenReturn(Mono.just(false));
        when(eventoPublisherPort.publicarEvento(evento)).thenReturn(Mono.error(new RuntimeException("Error de publicación")));
        eventoOrquestador.publicarEvento(evento);
        verify(eventoPublisherPort, times(1)).publicarEvento(evento);
    }
}