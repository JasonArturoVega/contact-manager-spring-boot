REST API for managing contacts, built with Java and Spring Boot.

## Features

- Create, list, edit, and delete contacts
- Search for contacts by: ID, phone, name (case-insensitive), email (case-insensitive)
- Phone number is mandatory and unique
- Email is optional (if provided, must contain `@`)
- 400 / 404 responses as appropriate
- In-memory H2 database

## Screenshots

(https://github.com/JasonArturoVega/contact-manager-spring-boot/blob/master/src/images/Screenshot%201.png)
(https://github.com/JasonArturoVega/contact-manager-spring-boot/blob/master/src/images/Screenshot%202.png)

## Technologies

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Maven

## Endpoints

| Method | Path | Description |
|--------|------|-------------|
| POST | `/contacts` | Create contact |
| GET | `/contacts` | List all |
| GET | `/contacts/by-id/{id}` | Search by ID |
| GET | `/contacts/search?phoneNumber=` | Search by phone number |
| GET | `/contacts/search?name=` | Search by name |
| GET | `/contacts/search?email=` | Search by email |
| PUT | `/contacts/by-id/{id}` | Edit contact |
| DELETE | `/contacts/by-id/{id}` | Delete contact |

### Creation example

```
{
"name": "Ana Pérez",
"phoneNumber": "912345678",
"email": "ana@mail.com"
}
```

### Search examples

```
GET /contacts/search?name=Ana
GET /contacts/search?phoneNumber=912345678
GET /contacts/search?email=ana@mail.com
```

## How to run

1. Clone the repository
2. Open it in IntelliJ IDEA
3. Run `ContactManagerSpringBootApplication`
4. The API is available at `http://localhost:8080`

## How to test

Use Postman or a similar tool to test the endpoints.

H2 Console (optional):

`http://localhost:8080/h2-console`

## Key takeaways

- Controller / Service / Repository structure
- Persistence with JPA
- Basic validations
- Searching with query parameters (`@RequestParam`)
- Handling 400 and 404 errors
