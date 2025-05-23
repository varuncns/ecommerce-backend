# 🛒 E-commerce Backend

A robust, modular, and production-ready backend for e-commerce platforms, built with **Spring Boot**, **MySQL**, and **JWT-based authentication**. The backend supports both admin and user roles, full CRUD for products, order management, cart functionality, and is cloud-ready for scalable deployments.

---

## 🚀 Tech Stack

- **Java 21**
- **Spring Boot 3.4.5**
- **Spring Security** (JWT-based authentication)
- **Hibernate (JPA)** & **MySQL**
- **Maven**
- **Swagger (OpenAPI via Springdoc)**
- **Docker** (deployment-ready)
- **Google Cloud Platform** (Cloud SQL, Cloud Run)
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

### 1. Clone the Repository

```bash
git clone https://github.com/varuncns/ecommerce-backend.git
cd ecommerce-backend
```

### 2. Configure MySQL

- Create a database/schema named `ecommerce_db`.
- Update credentials in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### 3. Build & Run the Application

```bash
./mvnw spring-boot:run
```

- Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 🗂️ Major Features

- **User & Admin Registration/Login** (`/auth/register`, `/auth/login`, `/auth/admin/register`)
- **JWT-based Authentication** & Stateless Sessions
- **Role-Based Access Control** (`ROLE_USER`, `ROLE_ADMIN`)
- **Product Management** (`/admin/products` CRUD)
- **Category Management** (`/admin/categories`, `/products/by-category`)
- **Cart & Order Management** (add/remove items, place orders, order history)
- **Pagination, Sorting, Filtering** for product listings
- **Swagger API Docs** with JWT support

---

## ✅ Milestone Progress

### Milestone 1: Project Initialization
- Spring Boot setup, MySQL integration

### Milestone 2: JWT Authentication
- Secure login/registration, password hashing

### Milestone 3: Role-Based Access Control
- User/admin roles, secure routes

### Milestone 4: Admin API & Registration
- Admin dashboard, admin registration

### Milestone 5: Product Management
- Create, list, update, delete products (admin-only)

### Milestone 6: Swagger & Enhanced Product API
- REST docs via Swagger, JWT integration

### Milestone 7: Google Cloud Deployment
- Dockerized app, Cloud SQL, Artifact Registry, Cloud Run

### Milestone 8: Categories, Pagination, Filtering
- Product-category linking, new endpoints, circular reference fixes

### Milestone 9: Cart & Order Management
- Cart and Order entities, endpoints, and logic

---

## 🛒 Cart Management

- Each user has one cart.
- Endpoints:
  - `POST /cart/add` – Add product to cart
  - `GET /cart` – View cart
  - `DELETE /cart/remove/{productId}` – Decrease quantity
  - `DELETE /cart/remove-all/{productId}` – Remove item
- Cart is cleared automatically after order placement.

## 📦 Order Management

- Place order directly from cart.
- Order includes total, timestamp, and item details.
- Status: `PENDING`, `PAID`, `SHIPPED`, `DELIVERED`, `CANCELLED`
- Endpoints:
  - `POST /order/place` – Place order
  - `GET /order/history` – Order history
  - `GET /order/{id}` – Order details
  - `PATCH /order/status/{id}?status=...` – Update status (admin only)

---

## ☁️ Cloud Deployment

### Architecture

```
Cloud Run ↔ Docker Image ↔ Artifact Registry
             ↕
          Cloud SQL (MySQL)
```

### GCP Setup Steps

#### 1. Enable Required APIs

```bash
gcloud services enable run.googleapis.com sqladmin.googleapis.com artifactregistry.googleapis.com
```

#### 2. Create Cloud SQL (MySQL) Instance

- Create database: `ecommerce_db`
- (For testing) Add authorized network: `0.0.0.0/0`

#### 3. Dockerfile Example

```dockerfile
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY target/ecommerce-backend-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java -Dspring.profiles.active=cloud -Dserver.port=$PORT -jar app.jar"]
```

#### 4. Build & Push Image

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

#### 6. Verify Deployment

Access the app:

```
https://ecommerce-backend-xxxxx.a.run.app
```

Test endpoints via Swagger UI or Postman (secured routes require JWT token).

---

## 📚 API Documentation

- **Local:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **Cloud:** `https://<your-cloud-url>/swagger-ui.html`
- Use `Bearer <JWT>` in your requests for secured endpoints.

---

## 🔜 Upcoming Features

- CI/CD: GitHub Actions or Cloud Build
- Private Docker registry integration
- End-to-end CI/CD with commit-based deployment
- More public APIs
- Enhanced test coverage

---

## 👤 Author

**Varun CNS**  
Java | Spring Boot | Cloud Native Development  
- [GitHub](https://github.com/varuncns)

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).
