# 🛒 E-commerce Backend

##  Tech Stack
- Java 21
- Spring Boot 3
- Spring Security + JWT
- Hibernate + MySQL
- Maven
- Docker (upcoming)
- Google Cloud Deployment (upcoming)

---

##  Prerequisites

- Java 17+ or 21
- MySQL (locally or Dockerized)
- Postman or curl
- Git installed
- IDE (IntelliJ / VS Code)

---

##  Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/varuncns/ecommerce-backend.git
   cd ecommerce-backend
   ```

2. Configure your database in `src/main/resources/application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
   spring.datasource.username=root
   spring.datasource.password=your_password
   ```

3. Run the application:

   ```bash
   mvn spring-boot:run
   ```

---

## ✅ Milestone Breakdown

---

###  Milestone 1: Spring Boot + MySQL Integration

* Initialized Spring Boot project
* Created Git repo and pushed initial code
* Connected MySQL with Spring Data JPA
* Verified connection + basic structure

---

###  Milestone 2: JWT Authentication System

* `POST /auth/register` → Register new users
* `POST /auth/login` → Login and receive JWT
* Passwords hashed using BCrypt
* Stateless JWT token generation & validation
* Configured JWT filter and security chain

---

###  Milestone 3: Authenticated User Access

* Created secure `/user/profile` endpoint
* Built and configured `JwtAuthFilter`
* Authenticated users validated from token
* Unauthorized access returns 403 or 401
* RBAC separation between public and protected routes

---

###  Milestone 4: Admin APIs + Role-Based Access Control (RBAC)

* Defined roles: `ROLE_USER`, `ROLE_ADMIN`
* `/admin/dashboard` → accessible only to admins
* `POST /auth/admin/register` → programmatically register admins
* Role loaded from DB → injected into JWT → validated by Spring Security
* Regular users blocked from admin routes


---

### Milestone 5: Product Module (Create + Read)

* Introduced Product entity with fields: productCode, name, price, stock, etc.
* POST /admin/products → Admins can create new products
* GET /products → Public can view all products
* Enforced uniqueness using productCode
* Validated access control: only admins can create; public can read
* Added business logic layer to prevent duplicates
* All tests passed: secure, clean, and public-facing

## 📮 API Endpoints (Completed)

| Method | Endpoint               | Access       |
| ------ | ---------------------- | ------------ |
| POST   | `/auth/register`       | Public       |
| POST   | `/auth/login`          | Public       |
| POST   | `/auth/admin/register` | Public       |
| GET    | `/user/profile`        | USER / ADMIN |
| GET    | `/admin/dashboard`     | ADMIN        |
|POST	   |`/admin/products	`      |ADMIN         |
|GET	   | `/products`	          |Public        |

