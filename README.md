# Airline Baggage Checking and Penalty Management System

A Spring Boot based backend application for managing airline baggage, passengers, baggage limits, excess weight and penalty calculation.

## Features

- Airline management
- Passenger management
- Baggage management
- Airline-specific baggage weight limits
- Automatic excess-weight calculation
- Automatic penalty calculation
- Baggage status tracking
- Dashboard summary
- REST APIs
- MySQL database integration
- Bean Validation
- Global exception handling
- Basic HTML/CSS/JavaScript frontend

## Tech Stack

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Bean Validation
- REST API
- HTML
- CSS
- JavaScript
- Git & GitHub

## Project Architecture

Frontend / Postman
        ↓
Controller
        ↓
Service
        ↓
Repository
        ↓
JPA / Hibernate
        ↓
MySQL

## Main Business Logic

The system checks the passenger's baggage against the baggage limit defined by their airline.

Example:

- Allowed baggage: 20 kg
- Actual baggage: 23 kg
- Excess baggage: 3 kg
- Penalty per kg: ₹600
- Total penalty: ₹1800

If the baggage weight is within the allowed limit, the baggage status is:

`WITHIN_LIMIT`

If the baggage weight exceeds the allowed limit, the baggage status is:

`EXCEEDED`

## API Endpoints

### Airline

- `POST /api/airlines`
- `GET /api/airlines`
- `GET /api/airlines/{id}`
- `PUT /api/airlines/{id}`
- `DELETE /api/airlines/{id}`

### Passenger

- `POST /api/passengers`
- `GET /api/passengers`
- `GET /api/passengers/{id}`
- `PUT /api/passengers/{id}`
- `DELETE /api/passengers/{id}`

### Baggage

- `POST /api/baggage`
- `GET /api/baggage`
- `GET /api/baggage/{id}`
- `PUT /api/baggage/{id}`
- `DELETE /api/baggage/{id}`

### Dashboard

- Dashboard summary API

## Database

The application uses MySQL.

Database name:

`airline_baggage_db`

Hibernate automatically manages the database tables using:

`spring.jpa.hibernate.ddl-auto=update`

## Running the Project

1. Clone the repository.
2. Open the project in IntelliJ IDEA.
3. Configure MySQL.
4. Set the `DB_PASSWORD` environment variable.
5. Run the Spring Boot application.
6. Open:

`http://localhost:8081/`

## Future Improvements

- JWT authentication
- Role-based access control
- Better frontend UI
- PDF baggage receipts
- Email notifications
- Deployment to cloud
