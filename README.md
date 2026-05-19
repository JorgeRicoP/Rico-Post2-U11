# PostContenido 2 - Unidad 11
# Refactorización Avanzada y Clean Code Profundo

---
## Tecnologías utilizadas:
    - Java 17
    - Spring Boot
    - Maven
    - SonarQube
    - JUnit 5
    - Docker

---

# Objetivo

El objetivo de esta actividad fue refactorizar condicionales complejos con alta complejidad ciclomática utilizando técnicas avanzadas de Clean Code y patrones de diseño.

Durante el laboratorio se trabajó principalmente con:

- Switch Statement Smell
- Arrow Code
- Alta Complejidad Ciclomática

Para solucionar estos problemas se aplicaron:

- Replace Conditional with Polymorphism
- Strategy Pattern
- Guard Clauses
- Open/Closed Principle

---

# Objetivo de la Refactorización

Se buscó:

- Reducir la complejidad ciclomática.
- Eliminar estructuras condicionales complejas.
- Mejorar la mantenibilidad.
- Facilitar la escalabilidad del sistema.
- Aplicar principios SOLID.
- Mantener el comportamiento original mediante pruebas automatizadas.

---

# Implementación Inicial

En la implementación inicial existían dos problemas principales:

---

# 1. Switch Statement Smell

El método `calcularEnvio()` utilizaba múltiples condicionales mediante `switch`.

```java
public double calcularEnvio(Pedido pedido, String tipoEnvio) {

    switch (tipoEnvio) {

        case "ESTANDAR":
            return pedido.getTotal() > 50 ? 0 : 5.99;

        case "EXPRESS":
            return 12.99;

        case "MISMO_DIA":
            return 24.99;

        case "GRATIS":
            return 0;

        default:
            throw new IllegalArgumentException(
                    "Tipo de envio desconocido: " + tipoEnvio);
    }
}
```

## Problemas detectados

- Alta complejidad ciclomática.
- Violación del principio Open/Closed.
- Difícil mantenimiento.
- Cada nuevo tipo de envío obligaba a modificar la clase.

---

# 2. Arrow Code

El método `aprobarCredito()` tenía múltiples estructuras anidadas.

```java
public String aprobarCredito(Cliente c, double monto) {

    if (c != null) {

        if (c.isActivo()) {

            if (c.getScore() >= 600) {

                if (monto > 0) {

                    if (monto <= c.getLimiteCredito()) {

                        return "APROBADO";
                    }
                }
            }
        }
    }

    return "RECHAZADO";
}
```

## Problemas detectados

- Código difícil de leer.
- Alta complejidad ciclomática.
- Mala mantenibilidad.
- Exceso de niveles de anidación.

---

# Pruebas Automatizadas

Antes de refactorizar se desarrollaron pruebas con JUnit 5 para garantizar que el comportamiento del sistema se mantuviera estable.

## EnvioServiceTest

```java
@Test
void calcularEnvio_estandar_conTotalAlto_debeSerGratis() {

    Pedido p = new Pedido();

    p.setTotal(60.0);

    assertEquals(
            0.0,
            service.calcularEnvio(p, "ESTANDAR"),
            0.001
    );
}
```

---

## CreditoServiceTest

```java
@Test
void aprobarCredito_clienteNulo_debeRechazar() {

    assertEquals(
            "RECHAZADO",
            service.aprobarCredito(null, 1000)
    );
}
```

---

# Refactorizaciones Aplicadas

# 1. Replace Conditional with Polymorphism

Se reemplazó el `switch` por múltiples estrategias independientes utilizando el patrón Strategy.

---

# EstrategiaEnvio

```java
public interface EstrategiaEnvio {

    double calcularCosto(Pedido pedido);
}
```

---

# EnvioEstandar

```java
@Component("ESTANDAR")
public class EnvioEstandar implements EstrategiaEnvio {

    @Override
    public double calcularCosto(Pedido pedido) {

        return pedido.getTotal() > 50 ? 0.0 : 5.99;
    }
}
```

---

# EnvioExpress

```java
@Component("EXPRESS")
public class EnvioExpress implements EstrategiaEnvio {

    @Override
    public double calcularCosto(Pedido pedido) {

        return 12.99;
    }
}
```

---

# EnvioMismoDia

```java
@Component("MISMO_DIA")
public class EnvioMismoDia implements EstrategiaEnvio {

    @Override
    public double calcularCosto(Pedido pedido) {

        return 24.99;
    }
}
```

---

# Nuevo EnvioService

```java
@Service
public class EnvioService {

    private final Map<String, EstrategiaEnvio> estrategias;

    public EnvioService(Map<String, EstrategiaEnvio> estrategias) {
        this.estrategias = estrategias;
    }

    public double calcularEnvio(Pedido pedido, String tipo) {

        return Optional.ofNullable(estrategias.get(tipo))
                .orElseThrow(() ->
                        new IllegalArgumentException(tipo))
                .calcularCosto(pedido);
    }
}
```

---

# Beneficios Obtenidos

- Eliminación del switch statement.
- Reducción de complejidad ciclomática.
- Código más extensible.
- Mejor separación de responsabilidades.
- Cumplimiento del principio Open/Closed.

---

# 2. Guard Clauses

Se reemplazó el arrow code por retornos tempranos.

## Código Refactorizado

```java
public String aprobarCredito(Cliente c, double monto) {

    if (c == null) return "RECHAZADO";

    if (!c.isActivo()) return "RECHAZADO";

    if (c.getScore() < 600) return "RECHAZADO";

    if (monto <= 0) return "RECHAZADO";

    if (monto > c.getLimiteCredito()) return "RECHAZADO";

    return "APROBADO";
}
```

---

# Beneficios de Guard Clauses

- Eliminación de anidaciones innecesarias.
- Código más limpio.
- Mejor legibilidad.
- Menor complejidad ciclomática.
- Flujo más fácil de entender.

---

# Análisis con SonarQube

Se realizaron dos análisis utilizando SonarQube:

1. Antes de aplicar Strategy Pattern y Guard Clauses.
2. Después de la refactorización.

---

# Métricas Comparativas

| Métrica | Antes | Después |
|---|---|---|
| Maintainability Issues | 7 | 13 |
| Reliability Issues | 0 | 0 |
| Security Issues | 0 | 0 |
| Coverage | 0.0% | 0.0% |
| Duplications | 0.0% | 0.0% |
| Complejidad Ciclomática calcularEnvio | 5 | 1 |
| Complejidad Ciclomática aprobarCredito | 6 | 2 |

---

# Resultados Obtenidos

Después de aplicar las técnicas de refactorización se logró:

- Reemplazar condicionales complejos utilizando polimorfismo.
- Implementar el patrón Strategy para el cálculo de envíos.
- Reducir significativamente la complejidad ciclomática.
- Aplicar Guard Clauses para eliminar arrow code.
- Mejorar la legibilidad y mantenibilidad del código.
- Facilitar la extensión del sistema sin modificar clases existentes.
- Mantener el comportamiento original validado mediante pruebas.

---

# Strategy Pattern y Open/Closed Principle

El patrón Strategy permitió desacoplar la lógica de cálculo de envío del servicio principal, facilitando la extensión del sistema sin modificar la clase `EnvioService`.

Gracias al principio Open/Closed, ahora es posible agregar nuevos tipos de envío creando nuevas implementaciones de `EstrategiaEnvio` sin alterar el código existente.

Esto mejora significativamente la mantenibilidad, escalabilidad y flexibilidad del proyecto.

---


# Comandos Utilizados

## Compilar proyecto

```bash
mvn compile
```

---

## Ejecutar pruebas

```bash
mvn test
```

---

## Ejecutar SonarQube en Docker

```bash
docker run -d --name sonarqube -p 9000:9000 sonarqube:community
```

---

## Ejecutar análisis SonarQube

```bash
mvn verify sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.token=TOKEN -Dsonar.projectKey=post2u11
```

---
