# PostContenido 1 - Unidad 11
## Refactorización Avanzada y Clean Code Profundo

---

# Objetivo

El objetivo de esta actividad fue identificar diferentes *code smells* dentro de un servicio Spring Boot y aplicar técnicas de refactorización para mejorar la mantenibilidad, legibilidad y complejidad ciclomática del código.

Durante el desarrollo del laboratorio se trabajó con los siguientes problemas de diseño:

- Long Method
- Large Class
- Primitive Obsession
- Data Clumps
- Inyección por campo

Posteriormente se aplicaron técnicas de refactorización como:

- Extract Method
- Extract Class
- Introducción de Value Objects
- Constructor Injection

---

# Estructura del Proyecto

```text
post1u11
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── jorgerico
│   │   │           └── post1u11
│   │   │               ├── entity
│   │   │               ├── repository
│   │   │               ├── service
│   │   │               └── valueobject
│   │   │
│   │   └── resources
│   │       └── application.properties
│   │
│   └── test
│
├── capturas
│
├── pom.xml
└── README.md
````

---
# Implementación Inicial
En la primera versión del proyecto se desarrolló una clase `PedidoService` con múltiples problemas intencionales de diseño.

---

# Problemas detectados

## Long Method

El método `procesarPedido()` realizaba demasiadas responsabilidades:

- Validación
- Cálculo de total
- Aplicación de descuentos
- Notificaciones
- Persistencia

Todo dentro del mismo método.

---

## Large Class

La clase `PedidoService` centralizaba demasiada lógica de negocio.

---

## Primitive Obsession

El método utilizaba demasiados parámetros primitivos:

```java
String clienteNombre,
String clienteEmail,
String clienteTelefono,
String clienteDireccion,
String clienteCiudad,
String clienteCodigoPostal
```

---

## Data Clumps

Muchos parámetros estaban relacionados entre sí y siempre viajaban juntos.

---

## Inyección por Campo

Inicialmente se utilizó:

```java
@Autowired
private PedidoRepository repo;
```

Lo cual no es recomendable.

---

# Refactorizaciones Aplicadas

## 1. Introducción de Value Objects

Se creó la clase `DatosCliente` para encapsular la información del cliente.

### Beneficios

- Eliminación de Primitive Obsession
- Eliminación de Data Clumps
- Validaciones centralizadas
- Mejor legibilidad

---

## 2. Creación de Direccion

Se creó un Value Object adicional llamado `Direccion`.

### Beneficios

- Mayor organización
- Encapsulamiento
- Modelo más limpio

---

## 3. Extract Method

El método `procesarPedido()` fue dividido en métodos pequeños y especializados:

```java
aplicarDescuento()
persistirPedido()
notificarPedido()
```

### Beneficios

- Menor complejidad ciclomática
- Mayor mantenibilidad
- Código más legible

---

## 4. Extract Class

La lógica de notificaciones fue movida a:

```java
NotificacionService
```

### Beneficios

- Responsabilidad única
- Separación de responsabilidades
- Mejor diseño orientado a objetos

---

## 5. Constructor Injection

Se reemplazó la inyección por campo por inyección por constructor.

### Antes

```java
@Autowired
private PedidoRepository repo;
```

### Después

```java
public PedidoService(PedidoRepository repo,
                     NotificacionService notificacion)
```

### Beneficios

- Mayor desacoplamiento
- Facilita pruebas
- Mejor práctica en Spring Boot

---

# Análisis con SonarQube

Se realizaron dos análisis utilizando SonarQube:

1. Antes de la refactorización
2. Después de la refactorización

---

# Métricas Comparativas

| Métrica | Antes | Después |
|---|---|---|
| Code Smells | 7 | 10 |
| Reliability Issues | 0 | 1 |
| Complejidad Ciclomática | Alta | Reducida |
| Coverage | 0.0% | 0.0% |
| Duplications | 0.0% | 0.0% |
| Maintainability Rating | A | A |

---

# Resultados Obtenidos

Después de aplicar las técnicas de refactorización se logró:

- Reducir la complejidad del método `procesarPedido()`
- Dividir responsabilidades en métodos más pequeños
- Implementar Value Objects para mejorar la organización de datos
- Separar la lógica de notificaciones en una clase independiente
- Mejorar la legibilidad y mantenibilidad del código
- Aplicar principios de Clean Code
- Utilizar inyección por constructor en lugar de inyección por campo

Aunque el número total de *Maintainability Issues* aumentó ligeramente en el análisis final, el código quedó mejor estructurado y con responsabilidades más separadas, lo que mejora la mantenibilidad a largo plazo.

---

# Comandos Utilizados

## Compilar proyecto

```bash
mvn compile
```

---

## Ejecutar SonarQube en Docker

```bash
docker run -d --name sonarqube -p 9000:9000 sonarqube:community
```

---

## Ejecutar análisis SonarQube

```bash
mvn verify sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.token=TOKEN -Dsonar.projectKey=post1u11
```

---