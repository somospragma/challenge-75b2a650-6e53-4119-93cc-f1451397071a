package com.pragma.integracion.infrastructure.adapter;

import com.pragma.integracion.domain.model.EventoTransaccional;
import com.pragma.integracion.domain.port.EventoPublisherPort;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class KafkaEventPublisher implements EventoPublisherPort {

    private final ReactiveKafkaProducerTemplate<String, String> kafkaTemplate;

    public KafkaEventPublisher(ReactiveKafkaProducerTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public Mono<Void> publicarEvento(EventoTransaccional evento) {
        ProducerRecord<String, String> record = new ProducerRecord<>("eventos-topic", evento.toString());
        return kafkaTemplate.send(record)
           .then();
    }
}