# Club Deportivo Los Cóndores - Microservicios

Sistema de gestión de canchas y reservas basado en arquitectura de microservicios.

## Arquitectura

```
┌─────────────────────┐     ┌─────────────────────┐
│  Microservicio      │     │  Microservicio      │
│  CANCHAS            │     │  RESERVAS           │
│  Puerto: 8081       │◄────│  Puerto: 8082       │
│                     │     │  (canchaId)         │
└─────────────────────┘     └─────────────────────┘
```

## Requisitos

- Java 21
- Maven (incluido via wrapper)

## Estructura del Proyecto

```
CanchasLosCondores/
├── demo/           # Microservicio Canchas (8081)
│   └── src/main/java/com/example/demo/
│       ├── model/Cancha.java
│       └── controller/CanchaController.java
│
└── reservas/       # Microservicio Reservas (8082)
    └── src/main/java/com/example/reservas/
        ├── model/Reserva.java
        └── controller/ReservaController.java
```

## Cómo Ejecutar

> **Importante:** Todos los comandos asumen que estás dentro de la carpeta `CanchasLosCondores/`

### 1. Microservicio Canchas (Terminal 1)

Abrir PowerShell y ejecutar:
```powershell
cd "C:\Users\Remmy\Desktop\CanchasLosCondores\demo"
.\mvnw.cmd spring-boot:run
```

El servicio estará disponible en: `http://localhost:8081`

### 2. Microservicio Reservas (Terminal 2)

Abrir **otra** ventana de PowerShell y ejecutar:
```powershell
cd "C:\Users\Remmy\Desktop\CanchasLosCondores\reservas"
.\mvnw.cmd spring-boot:run
```

El servicio estará disponible en: `http://localhost:8082`

## Endpoints Disponibles

### Microservicio Canchas (8081)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/canchas` | Lista todas las canchas |
| GET | `/canchas/{id}` | Obtiene una cancha por ID |
| POST | `/canchas` | Crea una nueva cancha |

### Microservicio Reservas (8082)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/reservas` | Lista todas las reservas |
| GET | `/reservas/{id}` | Obtiene una reserva por ID |
| GET | `/reservas/cancha/{canchaId}` | Filtra reservas por cancha |
| POST | `/reservas` | Crea una nueva reserva |

## Tutorial Rapido (Simple)

### 1. Levantar servicios

Terminal 1 (canchas):
```powershell
cd "C:\Users\Remmy\Desktop\CanchasLosCondores\demo"
.\mvnw.cmd spring-boot:run
```

Terminal 2 (reservas):
```powershell
cd "C:\Users\Remmy\Desktop\CanchasLosCondores\reservas"
.\mvnw.cmd spring-boot:run
```

### 2. Crear una reserva en Postman

1. Metodo: `POST`
2. URL: `http://localhost:8082/reservas`
3. Body: `raw` -> `JSON`
4. Header: `Content-Type: application/json`
5. JSON:

```json
{
  "canchaId": 1,
  "nombreCliente": "Ana",
  "fecha": "2025-06-20",
  "horaInicio": "10:00",
  "horaFin": "11:00"
}
```

### 3. Verificar que se guardo en MySQL (XAMPP)

```sql
USE los_condores;
SELECT id, cancha_id, cliente, fecha, hora_inicio, hora_fin
FROM reservas
ORDER BY id DESC
LIMIT 10;
```

## Nota Importante sobre Horarios

La tabla `reservas` ahora usa solo:

1. `hora_inicio`
2. `hora_fin`

La columna antigua `horario` fue eliminada para evitar errores 500 y mantener un unico formato de datos.

## Endpoints Utiles

- GET `http://localhost:8081/canchas`
- GET `http://localhost:8082/reservas`
- GET `http://localhost:8082/reservas/cancha/{canchaId}`
- POST `http://localhost:8082/reservas`
