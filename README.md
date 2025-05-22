# 🛒 E-commerce Backend

A robust and modular e-commerce backend built with **Spring Boot**, **MySQL**, and **JWT-based authentication**. Supports admin and user roles with full CRUD for product management.

---

## 🚀 Tech Stack

- Java 21
- Spring Boot 3.4.5
- Spring Security + JWT
- Hibernate (JPA) + MySQL
- Maven
- Swagger (OpenAPI via Springdoc)
- Docker (upcoming)
- Google Cloud Platform (✅ Milestone 7)

---

## 📦 Prerequisites

- Java 17+ or 21
- MySQL (local or GCP Cloud SQL)
- Postman or curl for API testing
- Git
- IDE (IntelliJ, VS Code)

---

## 🧪 Local Setup

### 1. Clone the repository

```bash
git clone https://github.com/varuncns/ecommerce-backend.git
cd ecommerce-backend
```

### 2. Configure MySQL

Create a schema named `ecommerce_db` and update credentials in your `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### 3. Run the application

```bash
./mvnw spring-boot:run
```

Swagger UI will be available at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

---

## ✅ Milestone Progress

### 🔹 Milestone 1 – Project Initialization
- Spring Boot setup with MySQL integration
- Git version control and project scaffolding

### 🔹 Milestone 2 – JWT Authentication
- `/auth/register`, `/auth/login` with password hashing
- JWT token generation and stateless authentication

### 🔹 Milestone 3 – Role-Based Access Control
- Defined `ROLE_USER` and `ROLE_ADMIN`
- Secured `/admin/**` endpoints using Spring Security

### 🔹 Milestone 4 – Admin API & Registration
- Admin registration endpoint (`/auth/admin/register`)
- Admin-only dashboard endpoint (`/admin/dashboard`)

### 🔹 Milestone 5 – Product Management
- `POST /admin/products` to create products
- `GET /admin/products/all` for listing
- Enforced unique `productCode`

### 🔹 Milestone 6 – Product Update/Delete + Swagger
- `PUT /admin/products` to update
- `DELETE /admin/products/{id}` to delete
- Swagger UI with JWT support and endpoint grouping

---

## ☁️ Milestone 7 – Deployment on Google Cloud Platform

Backend is deployed to **Google Cloud Run**:
- Dockerized Spring Boot app
- MySQL hosted on **Cloud SQL**
- Image pushed to **Artifact Registry**

## 🏁 Milestone 8 – Product & Category Integration + Pagination Support

- Category Entity & API endpoints added
- Product entity linked with Category via @ManyToOne
- `GET /products/by-category?category=Phones` added
- Pagination, sorting, and keyword filtering in /products
- Circular reference fix using @JsonIgnore

### Deployment Architecture

```
Cloud Run ↔ Docker Image ↔ Artifact Registry
             ↕
          Cloud SQL (MySQL)
```

### GCP Setup Steps

#### 1. Enable APIs

```bash
gcloud services enable run.googleapis.com sqladmin.googleapis.com artifactregistry.googleapis.com
```

#### 2. Create Cloud SQL (MySQL)
- Create database: `ecommerce_db`
- Add public IP authorized network: `0.0.0.0/0` (temporary for testing)

#### 3. Dockerfile Configuration

```dockerfile
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY target/ecommerce-backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java -Dspring.profiles.active=cloud -Dserver.port=$PORT -jar app.jar"]
```

#### 4. Build & Push to Artifact Registry

```bash
docker buildx create --use
docker buildx build --platform=linux/amd64 \
  -t us-central1-docker.pkg.dev/<PROJECT_ID>/ecommerce-docker-repo/ecommerce-backend \
  --push .
```

#### 5. Deploy to Cloud Run

```bash
gcloud run deploy ecommerce-backend \
  --image=us-central1-docker.pkg.dev/<PROJECT_ID>/ecommerce-docker-repo/ecommerce-backend \
  --platform=managed \
  --region=us-central1 \
  --allow-unauthenticated \
  --port=8080 \
  --set-env-vars=SPRING_PROFILES_ACTIVE=cloud
```

#### 6. Verify

Once deployed, access the app via:

```
https://ecommerce-backend-xxxxx.a.run.app
```

Test endpoints using Postman or Swagger UI (secured routes require JWT token).

---

## 📚 API Docs (Swagger UI)

- **Local:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **Cloud:** `https://<your-cloud-url>/swagger-ui.html`
- Use `Bearer <JWT>` to test secured endpoints

---

## 🔜 Upcoming: Milestones – Dockerization & CI/CD & Features like Cart, Order others

- GitHub Actions or Cloud Build for auto-deploy
- Private Docker registry integration
- End-to-end CI/CD setup with commit-based deployment
- Public APIs

---

## 👤 Author

**Varun CNS**  
Java | Spring Boot | Cloud Native Development  
[GitHub](https://github.com/varuncns)

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).
