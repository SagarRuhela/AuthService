# Auth Service - Setup & Run Guide

## 1. Prerequisites
- Java 17+
- Maven
- Docker & Docker Compose
- MySQL server

---

## 2. Setup MySQL Database
```sql
CREATE DATABASE authService;

CREATE TABLE roles (
    role_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

INSERT INTO roles (name) VALUES ('USER'), ('ADMIN');
```

---

## 3. Setup Kafka & Zookeeper
Create a file `docker-compose.yml`:

```yaml
version: '3'

services:
  zookeeper:
    image: confluentinc/cp-zookeeper:7.4.4
    environment:
      ZOOKEEPER_CLIENT_PORT: 2181
    ports:
      - "2181:2181"

  kafka:
    image: confluentinc/cp-kafka:7.4.4
    depends_on:
      - zookeeper
    ports:
      - "9092:9092"
      - "29092:29092"
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka:9092,PLAINTEXT_HOST://localhost:29092
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT
      KAFKA_INTER_BROKER_LISTENER_NAME: PLAINTEXT
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1
      KAFKA_LISTENERS: PLAINTEXT://0.0.0.0:9092,PLAINTEXT_HOST://0.0.0.0:29092
    volumes:
      - kafka-data:/var/lib/kafka/data

volumes:
  kafka-data:
```

Start Kafka & Zookeeper:
```bash
docker-compose up -d
```

Verify Kafka port:
```bash
nc -vz localhost 9092
```

---

## 4. Configure Spring Boot Application
Update `src/main/resources/application.properties`:

```properties
spring.application.name=Auth Service

# Database
spring.datasource.url=jdbc:mysql://localhost:3306/authService
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update

# Kafka Producer
spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.producer.key-serializer=org.apache.kafka.common.serialization.StringSerializer
spring.kafka.producer.value-serializer=org.springframework.kafka.support.serializer.JsonSerializer
spring.kafka.producer.acks=all
spring.kafka.producer.retries=3

# Kafka Consumer
spring.kafka.consumer.group-id=auth-service
spring.kafka.consumer.auto-offset-reset=earliest
spring.kafka.consumer.key-deserializer=org.apache.kafka.common.serialization.StringDeserializer
spring.kafka.consumer.value-deserializer=org.springframework.kafka.support.serializer.JsonDeserializer
spring.kafka.consumer.properties.spring.json.trusted.packages=*

spring.kafka.listener.concurrency=3
```

---

## 5. Run the Auth Service
```bash
mvn spring-boot:run
```

Expected log messages:
```text
Consumer clientId=consumer-auth-service-1, groupId=auth-service] Successfully joined group
auth-service: partitions assigned: [auth.user-registered.v1-0]
Auth Service ready to consume messages
```

---

## 6. Test API Endpoints
```markdown
| Method | Endpoint           | Description               |
|--------|------------------|---------------------------|
| POST   | /auth/register     | Register a new user       |
| POST   | /auth/login        | Login user & get JWT      |


```

---

## 7. Kafka Topic
```text
auth.user-registered.v1  -- triggered on user registration
```

---

## 8. Notes
```text
- Ensure Kafka is reachable at localhost:9092.
- Ensure MySQL credentials match application.properties.
- Consumer concurrency is 3 for parallel processing of messages.
```
