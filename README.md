## Library Management System

A Spring Boot RESTful API for managing a library's core operations: books, categories, authors, publishers, members, staff, and transactions. It uses MySQL for persistence, JPA/Hibernate for ORM, MapStruct for mapping, and exposes health/metrics via Spring Boot Actuator.

### Postman Collection
Use the curated Postman collection to explore and test the API endpoints:

- Postman Collection: [Library System API Collection](https://crimson-rocket-502695.postman.co/workspace/Personal-Workspace~ad93f884-1f52-4217-bc21-cdfa4e2f8bc8/collection/25088152-a826ae3a-7b79-4737-83ee-6f8ea9ef0f42?action=share&creator=25088152)

## Tech Stack
- Spring Boot 3.5.x (Web, Data JPA, Validation, Actuator)
- Java 21
- MySQL 8.x
- MapStruct 1.6.x
- Lombok
- Maven
```

### Configuration
Default configuration is in `LibrarySystem/src/main/resources/application.properties`:
```properties
spring.application.name=LibrarySystem
server.port=8080

```
Notes:
- Update `spring.datasource.username` and `spring.datasource.password` to match your local MySQL credentials.
- Ensure the directory in `spring.upload-dir` exists or change it to a valid path for your OS.
- `spring.jpa.hibernate.ddl-auto=update` will auto-create/update tables. You may also use `schema.sql` and `dummyData.sql` if you prefer manual initialization.

### Database Initialization (Optional)
- `schema.sql`: base schema
- `dummyData.sql`: sample data

You can run these directly in your MySQL client against the `library` database if desired.


### Base URLs
- API Base: `http://localhost:8080`
- Actuator: `http://localhost:8080/admin/actuator`

## API Usage
- Import and use the Postman collection to discover endpoints and example requests/responses.
- Typical resources include books, categories, members, authors, publishers, staff, and transactions.

## Project Structure (key parts)
```
LibrarySystem/
  src/main/java/code81/library/LibrarySystem/
    controller/       # REST controllers (Book, Category, Member, Admin, Librarian, ...)
    service/          # Services and service implementations
    repository/       # Spring Data JPA repositories
    entity/           # JPA entities
    dto/              # Request/response DTOs
    mapper/           # MapStruct mappers
  src/main/resources/
    application.properties
schema.sql
dummyData.sql


