# Integración del Core con el Bus de Eventos de Novedades

El equipo de desarrollo necesita integrar el sistema core bancario con el bus de eventos de novedades para asegurar que los eventos transaccionales se propaguen de manera idempotente y se manejen correctamente en caso de reproceso. Los eventos generados deben ser persistentes y garantizar que no se dupliquen en caso de reintentos. El sistema debe manejar un throughput de 10 000 eventos por segundo con una latencia máxima de 500ms. Se espera que el candidato identifique y maneje las posibles fallas del sistema externo (bus de eventos) y diseñe la integración para que sea resiliente y escalable.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Integracion orientada a eventos |
| **Nivel** | senior-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 10 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Exploración del Sistema Core y el Bus de Eventos

**Objetivo:** Comprender las funcionalidades y restricciones del sistema core y del bus de eventos.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Identificar las fuentes de eventos en el sistema core.
- Determinar las claves de negocio que garantizan la idempotencia de los eventos.
- Evaluar las restricciones de latencia y throughput del bus de eventos.

**Entregable:** Documento que describe las fuentes de eventos, las claves de negocio y las restricciones del bus de eventos.

<details>
<summary>Pistas de conocimiento</summary>

- Considera las posibles fuentes de eventos en el sistema core.
- Piensa en cómo garantizar que los eventos no se dupliquen en caso de reintentos.

</details>

### Fase 2: Diseño de la Integración Idempotente

**Objetivo:** Diseñar la integración del sistema core con el bus de eventos asegurando la idempotencia y el manejo de reproceso.

**Tiempo estimado:** 4 horas

**Instrucciones:**

- Diseñar el flujo de eventos desde el sistema core al bus de eventos.
- Implementar la lógica de idempotencia utilizando las claves de negocio identificadas.
- Manejar posibles fallas del bus de eventos y asegurar la resiliencia de la integración.

**Entregable:** Diseño detallado de la integración idempotente, incluyendo el flujo de eventos, la lógica de idempotencia y el manejo de fallas.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo almacenar y verificar las claves de negocio para garantizar la idempotencia.
- Piensa en estrategias para manejar la latencia y el throughput del bus de eventos.

</details>

### Fase 3: Implementación y Validación de la Integración

**Objetivo:** Implementar y validar la integración del sistema core con el bus de eventos.

**Tiempo estimado:** 4 horas

**Instrucciones:**

- Implementar la integración siguiendo el diseño detallado.
- Realizar pruebas unitarias y de integración para validar la idempotencia y el manejo de fallas.
- Asegurar que la integración cumpla con las restricciones de latencia y throughput.

**Entregable:** Implementación completa de la integración del sistema core con el bus de eventos, incluyendo pruebas unitarias y de integración.

<details>
<summary>Pistas de conocimiento</summary>

- Utiliza herramientas de testing para validar la idempotencia y el manejo de fallas.
- Verifica que la integración cumpla con las restricciones de latencia y throughput.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es la integración orientada a eventos y por qué es importante en sistemas distribuidos?
- **paraQueSirve**: ¿Para qué sirve la idempotencia en la integración de sistemas y cómo se aplica en este caso?
- **comoSeUsa**: ¿Cómo se usa la clave de negocio para garantizar la idempotencia en la integración?
- **erroresComunes**: ¿Cuáles son los errores comunes al integrar sistemas y cómo se pueden evitar en este caso?
- **queDecisionesImplica**: ¿Qué decisiones implica el diseño de la integración para asegurar la resiliencia y el cumplimiento de las restricciones de latencia y throughput?

## Criterios de Evaluacion

- Identificación correcta de las fuentes de eventos y las claves de negocio.
- Implementación de la lógica de idempotencia utilizando las claves de negocio.
- Manejo correcto de las posibles fallas del bus de eventos.
- Cumplimiento de las restricciones de latencia y throughput en la integración.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
