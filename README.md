# User Management System

A robust, "_backend-heavy_" full-stack application built with `Spring Boot 3` and `Thymeleaf`. This project demonstrates enterprise-grade architectural patterns, including DTO mapping, JPA Auditing, Server-side Pagination, Global Exception Handling, and Dockerized database management.

## 🚀 Features

- **Full-Stack CRUD**: Complete Create, Read, Update, Delete operations.

- **Backend-Heavy Architecture**:

  - **DTO Pattern**: Separation of internal Entity models from API Data Transfer Objects.

  - **JPA Auditing**: Automatic tracking of `createdAt` and `updatedAt` timestamps.

  - **Pagination & Sorting**: Efficient server-side data fetching using Spring Data JPA Pageable.

  - **Global Exception Handling**: Centralized controller advice for consistent JSON error responses.

  - **Input Validation**: Strict Jakarta Validation (@NotBlank, @Email) on incoming requests.

- **Modern UI**:

  - **Thymeleaf**: Server-side rendering.

  - **TailwindCSS**: Utility-first styling for a clean, responsive design.

  - **Interactive Elements**: SweetAlert2 for notifications, Modal forms, and dynamic page size selection.

  - **Dashboard**: Visual statistics using Chart.js.

- **Infrastructure**: Fully containerized PostgreSQL database using Docker Compose.

## 🛠️ Tech Stack

- **Language**: Java 17

- **Framework**: Spring Boot 3.2.0

- **Database**: PostgreSQL 16 (Alpine)

- **Template** Engine: Thymeleaf

- **Frontend**: Tailwind CSS, Chart.js, SweetAlert2, FontAwesome

- **Build Tool**: Maven

- **Containerization**: Docker & Docker Compose

## 📂 Project Structure

```bash
src
├── main
│   ├── java
│   │   └── th.co.nanon.demo
│   │       ├── dto                 # Data Transfer Objects (Validation Layer)
│   │       ├── model               # JPA Entities (Database Layer)
│   │       ├── repository          # Spring Data JPA Repositories
│   │       ├── service             # Business Logic
│   │       ├── controller          # REST & Web Controllers
│   │       ├── exception           # Global Exception Handling
│   │       └── NanonApplication.java
│   └── resources
│       ├── templates               # HTML Views (Thymeleaf)
│       └── application.properties  # Config
├── docker-compose.yml              # Database Orchestration
└── pom.xml                         # Dependencies
```

## ⚙️ Prerequisites

- Java 17 SDK or higher.

- Docker Desktop (for running the database).

- Maven (optional, wrapper is usually included).

## 🚀 How to Run

1. Start the Database

    Use **Docker Compose** to spin up the **PostgreSQL** container. This ensures you have a clean, isolated database environment.

    ```bash
    docker-compose up -d --build
    ```

    - DB URL: `jdbc:postgresql://localhost:5432/postgresdb`

    - User: `myuser`

    - Password: `mypassword`

2. Run the Application

    You can run the application using the Maven wrapper included in the project (or your installed Maven).

    ```bash
    ./mvnw spring-boot:run
    ```

    _Once started, the application will be available at: <http://localhost:8080>_

## 📖 Usage

### Web Interface

1. **Dashboard**: Navigate to `/dashboard` to view system statistics and charts.

2. **User List**: Navigate to `/` (Home).

    - **Search**: Use the search bar to filter by Name or Email.

    - **Pagination**: Use the "Rows per page" dropdown to change the view size (5, 10, 20, 50).

    - **CRUD**: Use the "Add User" button or the Edit/Delete icons in the table.

### REST API Endpoints

The backend exposes a fully functional RESTful APIs at `/api/users`.

Method | Endpoint | Description | Payload
--- | --- | --- | ---
GET | `/api/users` | List users (Paginated) | Params: page, size, keyword
GET | `/api/users/{id}` | Get single user | N/A
POST | `/api/users` | Create user | JSON: { "name": "...", "email": "...", "role": "..." }
PUT | `/api/users/{id}` | Update user | JSON: { "name": "...", "email": "...", "role": "..." }
DELETE | `/api/users/{id}` | Delete user | N/A

### Example cURL

```bash
# Create a user
curl -X POST <http://localhost:8080/api/users> \
     -H "Content-Type: application/json" \
     -d '{"name": "Test", "email": "test@example.com", "role": "Backend Developer"}'
```

## 🧪 Testing

To inspect the database content directly inside the Docker container:

```bash
docker exec -it postgres-db psql -U myuser -d postgresdb
# Then run this SQL query
SELECT * FROM "APP_USERS";
```

Created by **nanonymoussu** with ***heart*** ❤️
