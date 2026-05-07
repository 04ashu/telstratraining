# 🚀 Microservices Security
(Spring Boot + Eureka + API Gateway + Feign + JWT + MySQL)

This project demonstrates a complete **Microservices Architecture** using Spring Boot with:

- ✅ Netflix Eureka Service Discovery
- ✅ Spring Cloud API Gateway
- ✅ OpenFeign for inter-service communication
- ✅ MySQL Database
- ✅ JWT Authentication & Authorization
- ✅ Spring Security
- ✅ Stateless Authentication
- ✅ Role-based ready architecture

---

# 📦 Microservices Overview

The project contains the following microservices:

| Service | Description |
|---|---|
| 👤 User Service | User Management + JWT Authentication |
| 🛒 Product Service | Product Management |
| 📦 Order Service | Order Management |
| 🌐 API Gateway | Single Entry Point |
| 🧭 Eureka Server | Service Registry & Discovery |

---

# 🧭 Eureka Server

Registers all microservices dynamically.

### Default URL
```text
http://localhost:8761
```
---

# 🌐 API Gateway

Acts as the single entry point for all APIs.

## ✨ Features

- Route Management
- Load Balancing
- Service Discovery Integration
- JWT Security Ready

## 🌍 Default URL

```text
http://localhost:8080
```

---

# 📦 Services Overview

## 👤 User Service
- Manage users

### APIs
- `POST /users` → Create user
- `GET /users/{id}` → Get user by ID
- `GET /users` → Get all users

---

## 🛒 Product Service
- Manage products

### APIs
- `POST /products` → Create product
- `GET /products/{id}` → Get product by ID
- `GET /products` → Get all products

---

## 📦 Order Service
- Place orders
- Uses Feign Clients to call:
    - User Service
    - Product Service

### APIs
- `POST /orders` → Place order
- `GET /orders` → Get all orders

---

# 🔁 Feign Communication Flow

Order Service → User Service  
Order Service → Product Service

Example:
- Fetch user details
- Fetch product details
- Combine response

---

# 🔐 JWT Authentication Flow

1. User signs up using `/auth/signup`
2. Password gets encrypted using `BCryptPasswordEncoder`
3. User signs in using `/auth/signin`
4. JWT Token gets generated
5. Client stores JWT token
6. JWT token is sent in the `Authorization` header
7. `JwtAuthenticationFilter` validates the token
8. Spring Security authenticates the request
9. Secured APIs become accessible

---

# 🔑 Authorization Header

All secured APIs require the JWT token in the request header.

## Example

```http
Authorization: Bearer <JWT_TOKEN>
```

Example:

```http
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
```

---

# ⚙️ Tech Stack

- Java 17+
- Spring Boot
- Spring Security
- Spring Data JPA
- Spring Cloud
- Netflix Eureka
- Spring Cloud Gateway
- OpenFeign
- JWT
- MySQL
- Maven

---
# ▶️ Run Order

Start the microservices in the following order to ensure proper service registration and communication:

## 1️⃣ Start Eureka Server

Starts the Service Registry.

Default URL:
```text
http://localhost:8761
```

---

## 2️⃣ Start API Gateway

Acts as the single entry point for all microservices.

Default URL:
```text
http://localhost:8080
```

---

## 3️⃣ Start User Service

Handles:
- JWT Authentication
- User Management

Default URL:
```text
http://localhost:8081
```

---

## 4️⃣ Start Product Service

Handles Product Management APIs.

Default URL:
```text
http://localhost:8083
```

---

## 5️⃣ Start Order Service

Handles Order Management APIs and communicates with:
- User Service
- Product Service

using OpenFeign.

Default URL:
```text
http://localhost:8082
```

---

# ✅ Verify Services in Eureka Dashboard

Open:

```text
http://localhost:8761
```

Ensure all services are registered successfully:

- API-GATEWAY
- USER-SERVICE
- PRODUCT-SERVICE
- ORDER-SERVICE