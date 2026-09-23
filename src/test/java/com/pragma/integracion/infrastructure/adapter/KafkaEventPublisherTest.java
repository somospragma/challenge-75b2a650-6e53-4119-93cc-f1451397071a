package com.pragma.integracion.infrastructure.adapter;

import com.pragma.integracion.domain.model.EventoTransaccional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import reactor.core.publisher.Mono;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KafkaEventPublisherTest {

    @Mock
    private KafkaTemplate<String, EventoTransaccional> kafkaTemplate;

    @InjectMocks
    private KafkaEventPublisher kafkaEventPublisher;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testPublicarEvento() {
        EventoTransaccional evento = new EventoTransaccional("tipo", "cuenta");
        when(kafkaTemplate.sendDefault(evento)).thenReturn(Mono.empty());
        kafkaEventPublisher.publicarEvento(evento);
        verify(kafkaTemplate, times(1)).sendDefault(evento);
    }

    @Test
    void testManejoErrores() {
        EventoTransaccional evento = new EventoTransaccional("tipo", "cuenta");
        when(kafkaTemplate.sendDefault(evento)).thenReturn(Mono.error(new RuntimeException("Error de Kafka")));
        kafkaEventPublisher.publicarEvento(evento);
        verify(kafkaTemplate, times(1)).sendDefault(evento);
    }
}