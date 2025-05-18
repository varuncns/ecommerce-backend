# 🛒 E-commerce Backend

##  Tech Stack
- Java 21
- Spring Boot 3.4.5
- Spring Security + JWT
- Hibernate + MySQL
- Maven
- Swagger (Springdoc OpenAPI)
- Docker (upcoming)
- Google Cloud Deployment (upcoming)

---

##  Prerequisites

- Java 17+ or 21
- MySQL (locally or via Docker)
- Postman / curl for API testing
- Git installed
- IDE like IntelliJ or VS Code

---

##  Setup

1. Clone the repo:
   ```bash
   git clone https://github.com/varuncns/ecommerce-backend.git
   cd ecommerce-backend
   ```

2. Configure MySQL DB:
   - Create a schema named `ecommerce_db`
   - Use credentials in `src/main/resources/application.properties`:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
     spring.datasource.username=root
     spring.datasource.password=your_password
     spring.jpa.hibernate.ddl-auto=update
     ```

3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

---

## ✅ Milestones Summary

### 🧱 Milestone 1 – Spring Boot + DB + Git Integration
- Created clean folder structure
- Connected to MySQL DB
- Setup basic Spring Boot starter project
- Integrated Git + GitHub

### 🔐 Milestone 2 – Authentication System (JWT)
- Implemented `/auth/register` and `/auth/login`
- Used JWT for stateless authentication
- Integrated password hashing (BCrypt)
- Prevented duplicate registrations

### 🧑‍💼 Milestone 3 – Role-Based Access Setup
- Implemented `ROLE_USER` and `ROLE_ADMIN`
- Integrated JWT + Role extraction
- Restricted `/admin/**` to admins only
- Verified access with valid/invalid tokens

### 👨‍💼 Milestone 4 – Admin API + Admin Signup
- Introduced `/auth/admin/register`
- Created `GET /admin/dashboard` for role testing
- Allowed admin onboarding via API

### 📦 Milestone 5 – Product Management (Add + List)
- `POST /admin/products` to create new products
- `GET /admin/products/all` for listing
- Prevented duplicate `productCode`

### 🔄 Milestone 6 – Update/Delete + Swagger Docs
- `PUT /admin/products` to update a product
- `DELETE /admin/products/{id}` to delete
- Integrated **Swagger** at:
  - `http://localhost:8080/swagger-ui.html`
- Swagger setup includes:
  - JWT token header input (`Authorization`)
  - Role-based endpoint visibility
  - Easy testing without Postman

---

## 📚 Swagger Usage
- Base URL: `http://localhost:8080/swagger-ui.html`
- Add token via Authorize button: `Bearer <your_jwt_token>`
- Explore secured + public endpoints visually

---

Stay tuned for upcoming Milestones: Dockerization, Public Product APIs, Cart/Checkout Flow 🚀
