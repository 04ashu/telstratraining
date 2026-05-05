# Telstra Training Projects 🚀

This repository contains multiple projects created as part of training and hands-on practice across backend, microservices, and frontend technologies.

---

## 📂 Projects

### 🔹 Employee Service
- Spring Boot application using **Spring Data JPA** and **MySQL**
- Provides basic APIs:
    - `POST` → Save employee details
    - `GET` → Fetch employee details
- Includes:
    - Dockerfile to build the application image
    - Kubernetes deployment file (`k8s/app-deployment.yaml`)
- Can be deployed on **Minikube (WSL environment)**

📁 Path: `/employee`

---

### 🔹 Microservices Rate Limit
- Microservices-based architecture containing:
    - API Gateway
    - Eureka Server (Service Discovery)
    - Order Service
    - User Service
- Implements **global rate limiting at API Gateway level**
- Contains only basic APIs to validate rate limiting behavior

📁 Path: `/microservices-rate-limit`

---

### 🔹 Rate Limiter (Resilience4j)
- Standalone project to implement **rate limiting using Resilience4j**
- Includes a basic **Hello API** to test rate limiting functionality

📁 Path: `/rate-limiter`

---

### 🔹 React Apps
- Frontend project built using **React + Vite**
- Created to understand:
    - React fundamentals
    - Component-based architecture

📁 Path: `/reactapps`

---

## 🛠 Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Microservices (API Gateway, Eureka)
- Resilience4j
- Docker & Kubernetes
- React (Vite)

---

## 📌 Notes
- Each folder represents a standalone project
- Focus is on learning and implementing key concepts like:
    - Microservices architecture
    - Rate limiting
    - Containerization & deployment
    - Frontend basics with React  

