# Shopopedia Test Service

Shopopedia is designed as a Spring Boot microservices platform for an ecommerce flow that starts with user authentication and continues through product discovery, cart management, order placement, payment, inventory reservation, shipping, and user notifications.

This repository currently contains a single Spring Boot service named `test-service`. It exposes product data from PostgreSQL and can act as the starting point for the Product Service or a product catalog test service inside the wider Shopopedia platform.

## Platform Overview

The complete Shopopedia platform is planned as independently deployed microservices. Each service owns its domain logic and database access, while services communicate through REST APIs for synchronous requests and Kafka or RabbitMQ events for asynchronous workflows.

Primary services:

| Service | Responsibility |
| --- | --- |
| User Service | Handles user registration, login, authentication, and user profile data. |
| Product Service | Manages product catalog data stored in PostgreSQL. |
| Search Service | Provides product search and filtering capabilities. |
| Cart Service | Manages user carts and selected products before checkout. |
| Order Service | Creates orders and publishes order lifecycle events. |
| Payment Service | Consumes order events, processes payments, and publishes payment status events. |
| Inventory Service | Reserves stock and updates inventory quantities after successful payment. |
| Shipping Service | Calculates shipping, creates shipments, and generates tracking details. |
| Notification Service | Sends email or SMS updates for order, payment, and shipping events. |

## Order Flow

1. A user opens the Shopopedia application and logs in or registers through the User Service.
2. After authentication, the user browses products through the Product Service.
3. Product catalog data is fetched from PostgreSQL.
4. The user searches products through the Search Service.
5. The user adds products to the cart through the Cart Service.
6. When the user places an order, the request is sent to the Order Service.
7. The Order Service creates the order and publishes an `ORDER_CREATED` event.
8. The Payment Service consumes `ORDER_CREATED` and processes the payment.
9. After successful payment, the Payment Service publishes `PAYMENT_SUCCESS`.
10. The Inventory Service consumes the payment event, reserves stock, and updates inventory.
11. After inventory confirmation, the Shipping Service calculates shipping and creates tracking details.
12. The Notification Service sends email or SMS updates for order confirmation, payment status, and shipping updates.

## Architecture

```text
Client Application
        |
        v
User Service
        |
        v
Product Service <----> PostgreSQL
        |
        v
Search Service
        |
        v
Cart Service
        |
        v
Order Service -- ORDER_CREATED --> Kafka/RabbitMQ
                                      |
                                      v
                                Payment Service
                                      |
                         PAYMENT_SUCCESS event
                                      |
                                      v
                              Inventory Service
                                      |
                                      v
                               Shipping Service
                                      |
                                      v
                            Notification Service
```

## Technology Stack

- Java 21
- Spring Boot 4
- Spring Web MVC
- Spring Data JPA
- PostgreSQL
- Gradle
- Lombok
- Kafka or RabbitMQ for event-driven communication
- Docker for containerized deployment
- Kubernetes-ready deployment model

## Current Repository Status

This repository currently implements:

- Spring Boot application: `test-service`
- Server port: `8081`
- PostgreSQL datasource configuration for database `shopopedia`
- Product JPA entity mapped to the `products` table
- Product repository using Spring Data JPA
- REST endpoints for health check and product listing

Implemented endpoints:

| Method | Path | Description |
| --- | --- | --- |
| `GET` | `/health` | Returns service health text. |
| `GET` | `/products` | Returns all products from the PostgreSQL `products` table. |

## Local Setup

Prerequisites:

- Java 21
- PostgreSQL
- Gradle wrapper included in this repository

Create a local PostgreSQL database and user matching `src/main/resources/application.yaml`:

```sql
CREATE DATABASE shopopedia;
CREATE USER shopopedia_user WITH PASSWORD 'shopopedia_pass';
GRANT ALL PRIVILEGES ON DATABASE shopopedia TO shopopedia_user;
```

Run the service:

```bash
./gradlew bootRun
```

Health check:

```bash
curl http://localhost:8081/health
```

Fetch products:

```bash
curl http://localhost:8081/products
```

Run tests:

```bash
./gradlew test
```

## Configuration

Default application configuration is in `src/main/resources/application.yaml`.

Important defaults:

| Property | Value |
| --- | --- |
| `server.port` | `8081` |
| `spring.application.name` | `test-service` |
| `spring.datasource.url` | `jdbc:postgresql://localhost:5432/shopopedia` |
| `spring.datasource.username` | `shopopedia_user` |
| `spring.jpa.hibernate.ddl-auto` | `update` |

For production, database credentials should be supplied through environment variables, Kubernetes Secrets, or another external configuration mechanism instead of hardcoded values.

## Event Model

Planned domain events:

| Event | Producer | Consumer | Purpose |
| --- | --- | --- | --- |
| `ORDER_CREATED` | Order Service | Payment Service | Starts payment processing for a new order. |
| `PAYMENT_SUCCESS` | Payment Service | Inventory Service | Triggers stock reservation after payment succeeds. |
| `INVENTORY_RESERVED` | Inventory Service | Shipping Service | Allows shipping calculation and tracking generation. |
| `SHIPMENT_CREATED` | Shipping Service | Notification Service | Sends tracking updates to the user. |
| `PAYMENT_FAILED` | Payment Service | Order Service, Notification Service | Marks order payment failure and notifies the user. |
| `INVENTORY_RESERVATION_FAILED` | Inventory Service | Order Service, Notification Service | Handles unavailable stock after payment. |

## Deployment Notes

The platform is intended to be:

- Packaged as Docker images per service.
- Deployed independently.
- Scaled horizontally in Kubernetes.
- Configured through environment-specific settings.
- Integrated through REST APIs and event broker topics or queues.

Recommended Kubernetes components for the complete platform:

- Deployment per service
- Service per API-facing microservice
- ConfigMap for non-sensitive configuration
- Secret for database and broker credentials
- Ingress or API Gateway for external traffic
- PostgreSQL instance or managed database
- Kafka or RabbitMQ broker

## Suggested Next Steps

- Rename `test-service` to the final service name if this repository is meant to become the Product Service.
- Add service-layer classes instead of accessing repositories directly from controllers.
- Add DTOs and request/response models for public APIs.
- Add product search endpoints or split search into a dedicated Search Service.
- Add Dockerfile and Docker Compose for local PostgreSQL startup.
- Add Kubernetes manifests or Helm charts.
- Add integration tests using Testcontainers for PostgreSQL.
- Add OpenAPI documentation for REST endpoints.
