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
fullstack3/
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

> **Importante:** Todos los comandos asumen que estás dentro de la carpeta `fullstack3/`

### 1. Microservicio Canchas (Terminal 1)

Abrir PowerShell y ejecutar:
```powershell
cd "C:\Users\Remmy\Desktop\Duoc uc\5to semestre\fullstack3\demo"
.\mvnw.cmd spring-boot:run
```

El servicio estará disponible en: `http://localhost:8081`

### 2. Microservicio Reservas (Terminal 2)

Abrir **otra** ventana de PowerShell y ejecutar:
```powershell
cd "C:\Users\Remmy\Desktop\Duoc uc\5to semestre\fullstack3\reservas"
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

## Ejemplos con Postman/cURL

### Listar canchas
```bash
curl http://localhost:8081/canchas
```

### Crear cancha
```bash
curl -X POST http://localhost:8081/canchas \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Cancha B","tipo":"Fútbol","precioPorHora":15000}'
```

### Listar reservas
```bash
curl http://localhost:8082/reservas
```

### Crear reserva
```bash
curl -X POST http://localhost:8082/reservas \
  -H "Content-Type: application/json" \
  -d '{"canchaId":1,"nombreCliente":"Ana","fecha":"2025-06-20","horaInicio":"10:00","horaFin":"11:00"}'
```

### Filtrar reservas por cancha
```bash
curl http://localhost:8082/reservas/cancha/1
```

## Modelos de Datos

### Cancha
```json
{
  "id": 1,
  "nombre": "Cancha A",
  "tipo": "Fútbol",
  "estado": "Disponible",
  "precioPorHora": 12000
}
```

### Reserva
```json
{
  "id": 1,
  "canchaId": 1,
  "nombreCliente": "Juan Pérez",
  "fecha": "2025-06-18",
  "horaInicio": "09:00",
  "horaFin": "10:00"
}
```

## Datos de Prueba

Cada servicio incluye datos de prueba precargados:

**Canchas:**
- Cancha A (Fútbol, $12.000/hora)
- Cancha B (Tenis, $8.000/hora)
- Cancha C (Básquetbol, $10.000/hora - en mantenimiento)

**Reservas:**
- Juan Pérez - Cancha 1 - 2025-06-18
- María López - Cancha 2 - 2025-06-19
- Carlos Ruiz - Cancha 1 - 2025-06-20
