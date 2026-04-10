# FinAPP

Backend API для учёта доходов/расходов на Spring Boot + MongoDB.

## Запуск

```bash
./mvnw spring-boot:run
```

## Swagger UI

После запуска приложения доступны:

| Ресурс | URL                                    |
|--------|----------------------------------------|
| **Swagger UI** | http://localhost:7070/swagger-ui.html    |
| **OpenAPI JSON** | http://localhost:8080/v3/api-docs      |
| **OpenAPI YAML** | http://localhost:7070/v3/api-docs.yaml |

## API Endpoints

- `POST/GET/PUT/DELETE /tags` — управление тегами
- `POST/GET/PUT/DELETE /assets` — управление активами
- `POST/GET/PUT/DELETE /transactions` — управление транзакциями

## Технологии

- Java 21
- Spring Boot 4.0.3
- MongoDB
- SpringDoc OpenAPI (Swagger)
- Lombok
