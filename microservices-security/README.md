# 🚀 Microservices Security (Spring Boot + Eureka + API Gateway + Feign + MySQL)

This project demonstrates a **microservices architecture** using Spring Boot with:

- Service Discovery using Netflix Eureka
- API Gateway using Spring Cloud Gateway
- Inter-service communication using OpenFeign
- Database using MySQL
- Three core services:
    - 👤 User Service
    - 🛒 Product Service
    - 📦 Order Service

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

# ⚙️ Tech Stack

- Java 17+
- Spring Boot
- Spring Data JPA
- Spring Cloud
- OpenFeign
- MySQL
- Maven

---
# 🚀 Future Enhancements

- 🔐 JWT Authentication