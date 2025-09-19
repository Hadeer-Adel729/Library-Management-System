# Library Management System (Spring Boot)

A RESTful Library Management System built with Spring Boot 3, Spring Security (JWT), Spring Data JPA, and MySQL. It supports managing books, categories, members, staff, and transactions (borrow/return), with role-based access control for ADMIN endpoints.

### Postman Collection
Use the curated Postman collection to explore and test the API endpoints:

- Postman Collection: [Library System API Collection](https://documenter.getpostman.com/view/25088152/2sB3HroJqt)

## Prerequisites
- Java 21
- Maven 3.9+
- MySQL 8+
- cURL or an API client (e.g., Postman/Insomnia)

## Quick Start
1. Clone the repo and open it in your IDE or terminal.
2. Create a MySQL database (or let the app create it): `library`.
3. Update `src/main/resources/application.properties` if needed (DB credentials, port, JWT settings).
4. Run the app:
   - IDE: Run `LibrarySystemApplication`.
5. API base URL: `http://localhost:8080`.

## Configuration
Edit `src/main/resources/application.properties`:
- Server
  - `server.port=8080`
- Profiles and Actuator
  - `spring.profiles.active=test,dev,prod` (default = `test`)
  - Actuator base: `/admin/actuator`
- Database (MySQL)
  - `spring.datasource.url=jdbc:mysql://localhost:3306/library?createDatabaseIfNotExist=true`
  - `spring.datasource.username=root`
  - `spring.datasource.password=root`
- JPA
  - `spring.jpa.hibernate.ddl-auto=create-drop` (use `update` or `validate` in non-test envs)
  - `spring.jpa.show-sql=true`
- JWT
  - `application.security.jwt.secret-key=...` (Base64)
  - `application.security.jwt.expiration=86400000` (ms)
  - `application.security.jwt.refresh-token.expiration=604800000` (ms)

## Authentication & Authorization
- JWT-based stateless security.
- Public endpoints:
  - `POST /api/v1/auth/login`
- Protected endpoints require `Authorization: Bearer <accessToken>`.
- Role-based access via `@PreAuthorize("hasAuthority('ADMIN')")` on create/update/delete.
- Logout: `POST /api/v1/auth/logout` (stateless handler configured at `/api/v1/auth/logout`).

### Auth Flow
1. Login to obtain tokens.
2. Use `accessToken` in the `Authorization` header for subsequent requests.
3. Refresh token support exists via `JwtService` (endpoint not exposed in controllers).

#### Login
Request:
```http
POST /api/v1/auth/login
Content-Type: application/json

{
  "email": "admin@example.com",
  "password": "password"
}
```
Response:
```json
{
  "accessToken": "<jwt>",
  "refreshToken": "<jwt>"
}
```

#### Logout
```http
POST /api/v1/auth/logout
Authorization: Bearer <accessToken>
```

## API Reference
Base: `http://localhost:8080/api/v1`

Notes:
- Pagination: endpoints returning pages accept standard Spring parameters, e.g. `?page=0&size=10&sort=field,ASC`.
- Use `Authorization: Bearer <accessToken>` for protected endpoints.

### Books `/books`
- `GET /books` — List books (paged)
- `GET /books/{isbn}` — Get by ISBN
- `POST /books` — Create (ADMIN)
- `PUT /books` — Update (ADMIN)
- `DELETE /books/{id}` — Delete (ADMIN)
- `GET /books/filterByAuthor?author=NAME`
- `GET /books/filterByPublisher?publisher=NAME`
- `GET /books/filterByTitle?title=TITLE`
- `GET /books/filterByCategory?category=NAME`

Create example:
```http
POST /api/v1/books
Authorization: Bearer <accessToken>
Content-Type: application/json

{
  "isbn": "9780000000001",
  "title": "Example Book",
  "authorName": "Author",
  "publisherName": "Publisher",
  "categoryName": "Fiction",
  "copies": 5
}
```

### Categories `/categories`
- `GET /categories` — List all
- `GET /categories/{id}` — Get by id
- `GET /categories/filterByName?name=NAME`
- `POST /categories` — Create (ADMIN)
- `PUT /categories` — Update (ADMIN)
- `DELETE /categories/{id}` — Delete (ADMIN)

Create example:
```http
POST /api/v1/categories
Authorization: Bearer <accessToken>
Content-Type: application/json

{
  "id": null,
  "name": "Science"
}
```

### Members `/members`
- `GET /members` — List members (paged)
- `GET /members/{memderNumber}` — Get by membership number
- `POST /members` — Create (ADMIN)
- `PUT /members` — Update (ADMIN)
- `DELETE /members/{id}` — Delete (ADMIN)

Create example:
```http
POST /api/v1/members
Authorization: Bearer <accessToken>
Content-Type: application/json

{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john.doe@example.com",
  "phone": "+1234567890"
}
```

### Staff `/staff`
- `GET /staff/users` — List staff (ADMIN, paged)
- `GET /staff/users/{id}` — Get staff by id (ADMIN)

### Transactions `/transactions`
- `GET /transactions` — List transactions (paged)
- `GET /transactions/{id}` — Get by id
- `GET /transactions/status/{status}` — Filter by status (e.g., `PENDING`, `APPROVED`, `RETURNED`)
- `PATCH /transactions/borrowBook` — Borrow a book
- `PUT /transactions/returnBook` — Return a book

Borrow example:
```http
PATCH /api/v1/transactions/borrowBook
Authorization: Bearer <accessToken>
Content-Type: application/json

{
  "memberNumber": "MEM-0001",
  "isbn": "9780000000001"
}
```

Return example:
```http
PUT /api/v1/transactions/returnBook
Authorization: Bearer <accessToken>
Content-Type: application/json

{
  "transactionId": 123,
  "isbn": "9780000000001"
}
```

## Database & Seed Data
- Schema auto-creation is controlled by `spring.jpa.hibernate.ddl-auto`.
- Optional SQL files:
  - `schema.sql` — schema definition
  - `dummyData.sql` — sample data (run manually or configure `spring.sql.init.mode=always`).

## Building
- Package: `./mvnw clean package`
- Run JAR: `java -jar target/LibrarySystem-0.0.1-SNAPSHOT.jar`

## Project Structure
- `src/main/java/code81/library/LibrarySystem` — source code
- `controller` — REST controllers
- `service` — business logic
- `repository` — Spring Data JPA repositories
- `entity` — JPA entities
- `security` — JWT filter and security configuration
- `dto` — request/response models
- `mapper` — MapStruct mappers

## Troubleshooting
- 401 Unauthorized: Missing or invalid `Authorization` header.
- 403 Forbidden: Authenticated but lacking `ADMIN` authority.
- DB connection errors: verify MySQL is running and credentials match.
- On dev, consider setting `spring.jpa.hibernate.ddl-auto=update` to preserve data.
