# Explicaciones del Proyecto — Sistema de Asistencia

## ¿Por qué Clean Architecture?

El proyecto usa Clean Architecture (Robert C. Martin) con 4 capas. Las dependencias siempre apuntan hacia adentro — el dominio no conoce nada de Spring, JPA ni HTTP.

Esto significa que puedes cambiar la base de datos, el framework o el servidor de correo sin tocar las reglas de negocio.

---

## Los 4 Paquetes Raíz

| Paquete | Responsabilidad |
|---------|----------------|
| `domain` | Reglas de negocio puras — sin Spring ni JPA |
| `application` | Casos de uso — orquesta el dominio |
| `adapter` | Conecta con el mundo exterior (web, BD, correo) |
| `infrastructure` | Configuración Spring — conecta todo |

---

## Subpaquetes del Dominio

### `domain.model`
Las entidades del negocio: `Empleado`, `Marcacion`, `Horario`, etc.
Son POJOs puros — sin `@Entity`, sin `@Component`, sin nada de Spring.

### `domain.valueobject`
Valores inmutables que describen conceptos del negocio.
- `TipoMarcacion` — enum: `ENTRADA`, `SALIDA_ALMUERZO`, `ENTRADA_ALMUERZO`, `SALIDA_FINAL`
- `EstadoToken` — enum: `PENDIENTE`, `USADO`, `EXPIRADO`, `RECHAZADO`
- `EstadoMarcacion` — enum: `CORRECTA`, `TARDIA`, `INCOMPLETA`, `INVALIDA`
- `RangoIp` — encapsula validación de rangos CIDR (ej: 192.168.1.0/24)
- `Email` — value object con validación de formato

### `domain.repository`
Interfaces que definen **qué operaciones** se pueden hacer con los datos.
El dominio no sabe si la BD es MySQL, PostgreSQL o un archivo — solo define el contrato.

Ejemplo: `EmpleadoRepository` tiene métodos como `findById`, `save`, `findAll` — pero no sabe cómo están implementados.

### `domain.service`
Reglas de negocio complejas que involucran varias entidades.

| Servicio | Regla que implementa |
|---------|---------------------|
| `OrdenMarcacionService` | No puedes marcar SALIDA si no marcaste ENTRADA primero |
| `ValidacionHorarioService` | La marcación debe estar dentro del horario + tolerancia |
| `DeteccionTardanzaService` | Detecta si la marcación fue después de la tolerancia |
| `ValidacionTokenService` | El token no debe estar vencido, usado ni ser de otro empleado |

### `domain.exception`
Excepciones con significado de negocio — no son errores técnicos, son situaciones previstas.

| Excepción | Cuándo ocurre |
|-----------|--------------|
| `TokenVencidoException` | El enlace del correo expiró |
| `TokenYaUsadoException` | El enlace ya fue usado antes |
| `RedNoAutorizadaException` | El empleado no está en la red Wi-Fi de la empresa |
| `MarcacionFueraDeOrdenException` | Intenta marcar un tipo que no corresponde |
| `JornadaCompletadaException` | Ya tiene las 4 marcaciones del día |
| `MarcacionDuplicadaException` | Intenta marcar el mismo tipo dos veces |

---

## Value Objects — Detalle

### ¿Qué es un `record` en Java 21?
Un `record` es una clase inmutable por defecto. No necesitas getters, setters ni constructor manual.
El bloque `public NombreRecord { }` es el **constructor compacto** — ahí van las validaciones antes de crear el objeto.

```java
public record Email(String valor) {
    public Email {
        // validaciones aquí — si falla, lanza excepción y el objeto nunca se crea
    }
}
```

### ¿Qué es CIDR?
Es una notación para definir rangos de IPs. Ejemplo: `192.168.1.0/24` significa todas las IPs desde `192.168.1.0` hasta `192.168.1.255`.
Se usa para validar que el empleado está conectado a la red Wi-Fi de la empresa.

### Enums creados
| Enum | Valores |
|------|---------|
| `TipoMarcacion` | `ENTRADA`, `SALIDA_ALMUERZO`, `ENTRADA_ALMUERZO`, `SALIDA_FINAL` |
| `EstadoToken` | `PENDIENTE`, `USADO`, `EXPIRADO`, `RECHAZADO` |
| `EstadoMarcacion` | `CORRECTA`, `TARDIA`, `INCOMPLETA`, `INVALIDA` |

### Value objects creados
| Clase | Qué valida |
|-------|-----------|
| `Email` | Formato de correo electrónico |
| `RangoIp` | Rango de IPs en formato CIDR |

---

## Regla de Oro del Dominio

> El paquete `domain` **nunca** importa nada de Spring, JPA ni javax.
> Es Java puro — compila y funciona sin ningún framework.

Esto es lo que te permite defenderlo en la sustentación: puedes mostrar que las reglas de negocio son independientes de la tecnología.

---

## Flujo Principal del Sistema

```
Empleado solicita marcación
    → Sistema valida que está en red autorizada (IP)
    → Sistema valida el orden de marcación
    → Sistema genera token (UUID) y guarda su SHA-256
    → Sistema envía enlace por correo
    → Empleado hace clic en el enlace
    → Sistema valida el token (no vencido, no usado)
    → Sistema registra la marcación con hora del SERVIDOR
    → Sistema marca el token como USADO
```

**Regla crítica:** La hora de marcación SIEMPRE viene de `LocalDateTime.now()` en el servidor. El cliente nunca puede manipularla.
