# 🛒 E-commerce Backend

A robust, modular, and production-ready backend for e-commerce platforms, built with **Spring Boot**, **MySQL**, and **JWT-based authentication**. The backend supports both admin and user roles, featuring a comprehensive set of modules for seamless e-commerce operations.

---

## 🚀 Tech Stack

- **Java 21**
- **Spring Boot 3.4.5**
- **Spring Security** (JWT-based authentication)
- **Hibernate (JPA)** & **MySQL**
- **DTO Pattern** (Data Transfer Objects)
- **Maven**
- **Swagger (OpenAPI via Springdoc)**
- **Docker** (deployment-ready)
- **Google Cloud Platform** (Cloud SQL, Cloud Run)
- **Actuators** (Spring Boot monitoring)
- **Logging** (application-level logging)
- **Email Services**
- **CI/CD** (planned)

---

## 📦 Prerequisites

- Java 17+ or 21
- MySQL (local or GCP Cloud SQL)
- Git
- IDE (IntelliJ, VS Code, etc.)
- [Postman](https://www.postman.com/) or `curl` for API testing

---

## 🧪 Local Setup

1. **Clone the Repository**
    ```bash
    git clone https://github.com/varuncns/ecommerce-backend.git
    cd ecommerce-backend
    ```

2. **Configure MySQL**
    - Create a database/schema named `ecommerce_db`.
    - Update credentials in `src/main/resources/application.properties`:
        ```properties
        spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
        spring.datasource.username=root
        spring.datasource.password=your_password
        spring.jpa.hibernate.ddl-auto=update
        ```

3. **Build & Run the Application**
    ```bash
    ./mvnw spring-boot:run
    ```
    - Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 🗂️ Features

### ⚙️ Core Modules

- **User & Admin Registration/Login**  
  Secure registration and login endpoints for users and admins with JWT-based authentication.

- **Role-Based Access Control**  
  Fine-grained access via `ROLE_USER` and `ROLE_ADMIN`.

- **DTO Pattern**  
  Clean separation between entities and API payloads, improving security and maintainability.

- **Product Management**  
  Full CRUD for products (admin only), including pagination, sorting, and filtering.

- **Category Management**  
  Management of product categories and product-category associations.

- **Cart Management**  
  - Each user has a cart.
  - Endpoints to add, view, decrease quantity, or remove items.
  - Cart is cleared automatically after successful order placement.

- **Order Management**  
  - Place orders directly from the cart.
  - Order includes total, timestamp, and item details.
  - Status tracking: `PENDING`, `PAID`, `SHIPPED`, `DELIVERED`, `CANCELLED`.
  - Endpoints for placing orders, viewing history, and updating status (admin).

- **Address Module**  
  - Manage multiple addresses per user.
  - Add, update, or delete shipping/billing addresses.

- **Inventory Check**  
  - Automatic inventory validation during order placement.
  - Prevents overselling of products.

- **Admin Dashboard**  
  - Comprehensive dashboard for admins with:
    - Logistic details (order shipments, tracking, etc.)
    - Revenue details (sales statistics, revenue breakdown)

### 📧 Communication & Monitoring

- **Email Services**  
  - Send order confirmations, password resets, and other transactional emails.

- **Logging**  
  - Centralized and structured logging for debugging and monitoring.

- **Actuators**  
  - Spring Boot Actuator endpoints for health checks, metrics, and application monitoring.

### 🛡️ Security

- **JWT-based Authentication**  
  Stateless, secure sessions with token-based auth.

- **Role-based Route Protection**  
  Protects sensitive endpoints; only admins can access admin APIs.

---

## ☁️ Cloud Deployment

- **Dockerized** with deployment-ready Dockerfile.
- **Google Cloud Platform Integration**
  - Cloud SQL for managed MySQL
  - Artifact Registry for images
  - Cloud Run for serverless deployment

**Sample Dockerfile:**
```dockerfile
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY target/ecommerce-backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java -Dspring.profiles.active=cloud -Dserver.port=$PORT -jar app.jar"]
```

**Deploy via GCP CLI:**
```bash
gcloud run deploy ecommerce-backend \
  --image=us-central1-docker.pkg.dev/<PROJECT_ID>/ecommerce-docker-repo/ecommerce-backend \
  --platform=managed \
  --region=us-central1 \
  --allow-unauthenticated \
  --port=8080 \
  --set-env-vars=SPRING_PROFILES_ACTIVE=cloud
```

Access cloud deployment via  
`https://<your-cloud-url>/swagger-ui.html`

---

## 📚 API Documentation

- **Local:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **Cloud:** `https://<your-cloud-url>/swagger-ui.html`
- Use `Bearer <JWT>` in your requests for secured endpoints.

---

## 👤 Author

**Varun CNS**  
Java | Spring Boot | Cloud Native Development  
- [GitHub](https://github.com/varuncns)

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).
