# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Integración del Core con el Bus de Eventos de Novedades**.

| | |
|---|---|
| Tema | Integracion orientada a eventos |
| Nivel | senior-l2 |
| Chapter | Integración — Desarrollo |
| Especialidad | Transaccional |
| Stack | Java / Spring Boot 3.5.6 |
| Patron arquitectonico | hexagonal/clean con capas reactivas |
| Tiempo estimado | 10 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml con las rutas y endpoints externos`
- `domain con el modelo canonico del mensaje`
- `application con el orquestador de la integracion`
- `infrastructure con productor, consumidor y cliente del sistema externo`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos, la del parent y la de cada dependencia. Dos segmentos no resuelven.
- Apache Camel se integra con `camel-spring-boot-starter` y su BOM `camel-spring-boot-bom`. La version del BOM tiene que EXISTIR: si no podes afirmar cual es, usa solo el starter sin BOM en vez de inventar un numero (`camel-spring-boot-bom:3.20.0` no existe y mata el build).
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.

Dependencias:

- org.springframework.boot:spring-boot-starter-webflux 3.5.6
- org.springframework.boot:spring-boot-starter-actuator 3.5.6
- org.springframework.kafka:spring-kafka 3.5.0
- org.apache.camel:camel-spring-boot-starter 4.8.0
- io.github.resilience4j:resilience4j-spring-boot2 2.2.0
- io.projectreactor:reactor-core 3.6.8
- org.springframework.boot:spring-boot-starter-test 3.5.6
- org.junit.jupiter:junit-jupiter-api 5.10.2
- org.mockito:mockito-core 5.12.0
- org.apache.kafka:kafka-clients 3.5.0

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Exploración del Sistema Core y el Bus de Eventos**: Documento que describe las fuentes de eventos, las claves de negocio y las restricciones del bus de eventos.
- **Fase 2 — Diseño de la Integración Idempotente**: Diseño detallado de la integración idempotente, incluyendo el flujo de eventos, la lógica de idempotencia y el manejo de fallas.
- **Fase 3 — Implementación y Validación de la Integración**: Implementación completa de la integración del sistema core con el bus de eventos, incluyendo pruebas unitarias y de integración.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Superficie de practica (NO completes)

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs. No toques la logica que el reto pide completar.

- [ ] `src/main/java/com/pragma/integracion/infrastructure/config/ResilienceConfig.java` — El topic pide resiliencia: este archivo es el ejercicio.
- [ ] `src/test/java/com/pragma/integracion/application/EventoOrquestadorTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/pragma/integracion/infrastructure/adapter/KafkaEventPublisherTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.

## Lo que falta y tenes que completar

### 1. Referencias colgando (13)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/pragma/integracion/IntegracionApplication.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Hooks pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/integracion/domain/port/EventoPublisherPort.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/integracion/domain/port/IdempotenciaRepositoryPort.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/IdempotenciaRepository.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/integracion/application/EventoOrquestador.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/KafkaEventPublisher.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/integracion/infrastructure/config/ResilienceConfig.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/pragma/integracion/application/EventoOrquestadorTest.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/pragma/integracion/infrastructure/adapter/KafkaEventPublisherTest.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/pragma/integracion/infrastructure/adapter/KafkaEventPublisher.java` — `EventoTransaccional.toString`
      Se invoca `toString` sobre `EventoTransaccional`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/pragma/integracion/application/EventoOrquestadorTest.java` — `EventoOrquestador.publicarEvento`
      Se invoca `publicarEvento` sobre `EventoOrquestador`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `pom.xml` — `org.springframework.kafka:spring-kafka@3.5.0`
      org.springframework.kafka:spring-kafka declara la version 3.5.0, pero Maven Central respondio que esa version no existe. Es una version inventada: reemplazala por una version publicada real, o si no se conoce con certeza, usa el mecanismo centralizado del ecosistema (BOM/parent/platform/version catalog) y no declares una version individual.
- [ ] `pom.xml` — `org.apache.camel:camel-spring-boot-bom@4.8.0`
      org.apache.camel:camel-spring-boot-bom declara la version 4.8.0, pero Maven Central respondio que esa version no existe. Es una version inventada: reemplazala por una version publicada real, o si no se conoce con certeza, usa el mecanismo centralizado del ecosistema (BOM/parent/platform/version catalog) y no declares una version individual.

### Presentes (14)

- `pom.xml`
- `src/main/java/com/pragma/integracion/IntegracionApplication.java`
- `src/main/resources/application.yml`
- `src/main/java/com/pragma/integracion/domain/model/EventoTransaccional.java`
- `src/main/java/com/pragma/integracion/domain/port/EventoPublisherPort.java`
- `src/main/java/com/pragma/integracion/domain/port/IdempotenciaRepositoryPort.java`
- `src/main/java/com/pragma/integracion/infrastructure/adapter/IdempotenciaRepository.java`
- `src/main/java/com/pragma/integracion/application/EventoOrquestador.java`
- `src/main/java/com/pragma/integracion/infrastructure/adapter/KafkaEventPublisher.java`
- `src/main/java/com/pragma/integracion/infrastructure/config/KafkaConfig.java`
- `src/main/java/com/pragma/integracion/infrastructure/config/ResilienceConfig.java`
- `src/test/java/com/pragma/integracion/application/EventoOrquestadorTest.java`
- `src/test/java/com/pragma/integracion/infrastructure/adapter/KafkaEventPublisherTest.java`
- `README.md`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/pragma/integracion`
- `src/main/java/com/pragma/integracion/application`
- `src/main/java/com/pragma/integracion/domain`
- `src/main/java/com/pragma/integracion/domain/model`
- `src/main/java/com/pragma/integracion/domain/port`
- `src/main/java/com/pragma/integracion/infrastructure`
- `src/main/java/com/pragma/integracion/infrastructure/adapter`
- `src/main/java/com/pragma/integracion/infrastructure/config`
- `src/main/resources`
- `src/test/java/com/pragma/integracion`

## Verificacion

```bash
mvn clean compile
```

El comando tiene que pasar SIN implementar los archivos de la superficie de practica: solo andamiaje.

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **hexagonal/clean con capas reactivas**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Integración, Especialidad Desarrollador, Tecnología Transaccional, Senior
- Brecha que el reto ataca: Se acopla a sistemas basados en eventos aplicando idempotencia por clave de negocio y manejo de reproceso
- Mision: Integrar el core con el bus de eventos de novedades

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
