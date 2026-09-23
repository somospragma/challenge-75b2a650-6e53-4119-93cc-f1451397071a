# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/main/java/com/pragma/integracion/infrastructure/config/ResilienceConfig.java` — El topic pide resiliencia: este archivo es el ejercicio.
- `src/test/java/com/pragma/integracion/application/EventoOrquestadorTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/pragma/integracion/infrastructure/adapter/KafkaEventPublisherTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/pragma/integracion/IntegracionApplication.java` — `reactor.core.publisher`: El import reactor.core.publisher.Hooks pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/integracion/domain/port/EventoPublisherPort.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/integracion/domain/port/IdempotenciaRepositoryPort.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/integracion/infrastructure/adapter/IdempotenciaRepository.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/integracion/application/EventoOrquestador.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/integracion/infrastructure/adapter/KafkaEventPublisher.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/integracion/infrastructure/config/ResilienceConfig.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/pragma/integracion/application/EventoOrquestadorTest.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/pragma/integracion/infrastructure/adapter/KafkaEventPublisherTest.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/pragma/integracion/infrastructure/adapter/KafkaEventPublisher.java` — `EventoTransaccional.toString`: Se invoca `toString` sobre `EventoTransaccional`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/pragma/integracion/application/EventoOrquestadorTest.java` — `EventoOrquestador.publicarEvento`: Se invoca `publicarEvento` sobre `EventoOrquestador`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `pom.xml` — `org.springframework.kafka:spring-kafka@3.5.0`: org.springframework.kafka:spring-kafka declara la version 3.5.0, pero Maven Central respondio que esa version no existe. Es una version inventada: reemplazala por una version publicada real, o si no se conoce con certeza, usa el mecanismo centralizado del ecosistema (BOM/parent/platform/version catalog) y no declares una version individual.
- `pom.xml` — `org.apache.camel:camel-spring-boot-bom@4.8.0`: org.apache.camel:camel-spring-boot-bom declara la version 4.8.0, pero Maven Central respondio que esa version no existe. Es una version inventada: reemplazala por una version publicada real, o si no se conoce con certeza, usa el mecanismo centralizado del ecosistema (BOM/parent/platform/version catalog) y no declares una version individual.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Integración, Especialidad Desarrollador, Tecnología Transaccional, Senior

### Brecha de conocimiento
Se acopla a sistemas basados en eventos aplicando idempotencia por clave de negocio y manejo de reproceso

### Misión / candidato
Integrar el core con el bus de eventos de novedades

### Datos adicionales
Candidato con 5 años en integracion

### Reto
- Tema: Integracion orientada a eventos
- Seniority: senior-l2
- Tipo: practical
- Título: Integración del Core con el Bus de Eventos de Novedades
- Tiempo estimado: 10 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Exploración del Sistema Core y el Bus de Eventos — objetivo: Comprender las funcionalidades y restricciones del sistema core y del bus de eventos. — entregable (NO resolver): Documento que describe las fuentes de eventos, las claves de negocio y las restricciones del bus de eventos.
- Fase 2: Diseño de la Integración Idempotente — objetivo: Diseñar la integración del sistema core con el bus de eventos asegurando la idempotencia y el manejo de reproceso. — entregable (NO resolver): Diseño detallado de la integración idempotente, incluyendo el flujo de eventos, la lógica de idempotencia y el manejo de fallas.
- Fase 3: Implementación y Validación de la Integración — objetivo: Implementar y validar la integración del sistema core con el bus de eventos. — entregable (NO resolver): Implementación completa de la integración del sistema core con el bus de eventos, incluyendo pruebas unitarias y de integración.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.pragma</groupId>
    <artifactId>integracion</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>integracion</name>
    <description>Integración del Core con el Bus de Eventos de Novedades</description>

    <properties>
        <java.version>21</java.version>
        <camel.version>4.8.0</camel.version>
        <resilience4j.version>2.2.0</resilience4j.version>
        <reactor.version>3.6.8</reactor.version>
    </properties>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.apache.camel</groupId>
                <artifactId>camel-spring-boot-bom</artifactId>
                <version>${camel.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-webflux</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.kafka</groupId>
            <artifactId>spring-kafka</artifactId>
            <version>3.5.0</version>
        </dependency>

        <!-- Apache Camel -->
        <dependency>
            <groupId>org.apache.camel</groupId>
            <artifactId>camel-spring-boot-starter</artifactId>
        </dependency>

        <!-- Resilience4j -->
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-spring-boot2</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-reactor</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>

        <!-- Project Reactor -->
        <dependency>
            <groupId>io.projectreactor</groupId>
            <artifactId>reactor-core</artifactId>
            <version>${reactor.version}</version>
        </dependency>

        <!-- Kafka Clients -->
        <dependency>
            <groupId>org.apache.kafka</groupId>
            <artifactId>kafka-clients</artifactId>
            <version>3.5.0</version>
        </dependency>

        <!-- Testing -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.10.2</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <version>5.12.0</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>${java.version}</source>
                    <target>${java.version}</target>
                </configuration>
            </plugin>
        </plugins>
    </build>

    <repositories>
        <repository>
            <id>spring-milestones</id>
            <name>Spring Milestones</name>
            <url>https://repo.spring.io/milestone</url>
        </repository>
    </repositories>
</project>

// === ARCHIVO: src/main/java/com/pragma/integracion/IntegracionApplication.java ===
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

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: integracion-core-eventos
  kafka:
    bootstrap-servers: ${KAFKA_BOOTSTRAP_SERVERS:localhost:9092}
    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.springframework.kafka.support.serializer.JsonSerializer
      retries: 3
      acks: all
      properties:
        linger.ms: 5
        batch.size: 16384
    consumer:
      group-id: integracion-core-group
      auto-offset-reset: earliest
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.springframework.kafka.support.serializer.JsonDeserializer
      properties:
        spring.json.trusted.packages: com.pragma.integracion.domain
        spring.json.value.default.type: com.pragma.integracion.domain.EventoTransaccional

management:
  endpoints:
    web:
      exposure:
        include: health,metrics,info,prometheus
  endpoint:
    health:
      show-details: always
  metrics:
    export:
      prometheus:
        enabled: true

camel:
  springboot:
    name: IntegracionCoreEventos
    main-run-controller: true
    jmx-enabled: true
    tracing: true
    producer-template-cache-size: 100
    consumer-template-cache-size: 100

resilience4j:
  circuitbreaker:
    configs:
      default:
        registerHealthIndicator: true
        slidingWindowSize: 10
        minimumNumberOfCalls: 5
        permittedNumberOfCallsInHalfOpenState: 3
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 1000ms
        failureRateThreshold: 50
        eventConsumerBufferSize: 10
        recordExceptions:
          - org.springframework.kafka.KafkaException
          - java.io.IOException
          - java.util.concurrent.TimeoutException
    instances:
      kafkaPublisher:
        baseConfig: default
  retry:
    configs:
      default:
        maxAttempts: 3
        waitDuration: 500ms
        retryExceptions:
          - org.springframework.kafka.KafkaException
          - java.io.IOException
          - java.util.concurrent.TimeoutException
    instances:
      kafkaPublisher:
        baseConfig: default

app:
  eventos:
    topic-novedades: ${TOPIC_NOVEDADES:novedades-transaccionales}
    topic-reprocesos: ${TOPIC_REPROCESOS:reprocesos-transaccionales}
    consumer-group: ${CONSUMER_GROUP:integracion-core-group}
    idempotencia-ttl: 86400000 # 24 horas en milisegundos
    batch-size: 100
    poll-timeout: 100
    max-poll-records: 500

// === ARCHIVO: src/main/java/com/pragma/integracion/domain/model/EventoTransaccional.java ===
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

// === ARCHIVO: src/main/java/com/pragma/integracion/domain/port/EventoPublisherPort.java ===
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

// === ARCHIVO: src/main/java/com/pragma/integracion/domain/port/IdempotenciaRepositoryPort.java ===
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

// === ARCHIVO: src/main/java/com/pragma/integracion/infrastructure/adapter/IdempotenciaRepository.java ===
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

// === ARCHIVO: src/main/java/com/pragma/integracion/application/EventoOrquestador.java ===
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

// === ARCHIVO: src/main/java/com/pragma/integracion/infrastructure/adapter/KafkaEventPublisher.java ===
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

// === ARCHIVO: src/main/java/com/pragma/integracion/infrastructure/config/KafkaConfig.java ===
package com.pragma.integracion.infrastructure.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {
    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public NewTopic transactionalEventsTopic() {
        return new NewTopic("transactional-events", 1, (short) 1);
    }
}

// === ARCHIVO: src/main/java/com/pragma/integracion/infrastructure/config/ResilienceConfig.java ===
package com.pragma.integracion.infrastructure.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import io.github.resilience4j.reactor.retry.operator.RetryOperator;
import io.github.resilience4j.retry.RetryConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class ResilienceConfig {
    @Bean
    public CircuitBreakerOperator circuitBreakerOperator() {
        return CircuitBreakerOperator.of("event-publisher-circuit-breaker",
            CircuitBreakerConfig.custom().build());
    }

    @Bean
    public RetryOperator retryOperator() {
        return RetryOperator.of(RetryConfig.custom().maxAttempts(3).build());
    }

    @Bean
    public Mono<String> fallbackMethod() {
        return Mono.just("Fallback response");
    }
}

// === ARCHIVO: src/test/java/com/pragma/integracion/application/EventoOrquestadorTest.java ===
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

// === ARCHIVO: src/test/java/com/pragma/integracion/infrastructure/adapter/KafkaEventPublisherTest.java ===
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

// === ARCHIVO: README.md ===
# Integración del Core con el Bus de Eventos de Novedades

## Descripción
Este proyecto integra el sistema core bancario con el bus de eventos de novedades para asegurar que los eventos transaccionales se propaguen de manera idempotente y se manejen correctamente en caso de reproceso.

## Arquitectura
El proyecto sigue un patrón hexagonal/clean con capas reactivas. La arquitectura se divide en las siguientes capas:
- **Dominio**: Contiene los modelos y las reglas de negocio.
- **Aplicación**: Orquestra la lógica de negocio y la publicación de eventos.
- **Infraestructura**: Maneja la publicación y recepción de eventos en Kafka.

## Decisiones Clave
- Uso de records para DTOs y modelos de dominio en Java 21.
- Separación estricta entre dominio e infraestructura siguiendo el patrón hexagonal.
- Manejo reactivo de streams con Project Reactor para cumplir con requisitos de throughput y latencia.
- Configuración de Resilience4j con políticas de retry y circuit breaker adaptadas a las restricciones del bus de eventos.
- Claves de idempotencia almacenadas en un repositorio dedicado para garantizar consistencia.
- Uso de Jakarta EE en lugar de javax.* en todas las anotaciones y clases.

## Comandos para Ejecutar
Para ejecutar el proyecto, use los siguientes comandos:

```
# Compilar y ejecutar
mvn spring-boot:run
```
```
