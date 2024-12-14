# Student Management API

This project is a **Student Management REST API** built with **Spring Boot**. It provides endpoints for user authentication, subject management, and student management. The application uses JWT for secure access and role-based authorization.

## Features
1. **User Authentication**
   - Login using username and password.
   - Generates JWT tokens for secured API access.
2. **Role-Based Access Control**
   - Admin users can perform all operations.
3. **Subject Management**
   - Add new subjects.
   - Retrieve a list of all subjects.
4. **Student Management**
   - Add new students.
   - Retrieve a list of all students.
   - Students can be associated with subjects.

## Tech Stack
- **Java** (JDK 17)
- **Spring Boot**
- **Spring Security**
- **JWT (JSON Web Token)**
- **Spring Data JPA**
- **H2 Database** (for development)
- **Maven**

## Prerequisites
- Java 17 or later
- Maven

---

## Project Setup

1. **Clone the Repository**
   ```bash
   git clone <repository_url>
   cd server
   ```

2. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```
   The application will start at `http://localhost:8080`.

3. **Database**
   - The project uses H2 (in-memory database) for development.
   - Data is pre-populated via `data.sql`.

---

## API Endpoints

### Authentication
| Method | Endpoint           | Description               |
|--------|--------------------|---------------------------|
| POST   | `/api/auth/login`  | Authenticate and generate JWT token. |

#### Example Login Request
```json
POST /api/auth/login
{
    "username": "admin",
    "password": "admin_password"
}
```
#### Example Response
```json
{
    "token": "<jwt_token>"
}
```

---

### Subjects API
| Method | Endpoint           | Description             |
|--------|--------------------|-------------------------|
| GET    | `/api/subjects`    | Retrieve all subjects.  |
| POST   | `/api/subjects`    | Add a new subject.      |

#### Example: Add Subject
```json
POST /api/subjects
{
    "name": "Math"
}
```

---

### Students API
| Method | Endpoint           | Description               |
|--------|--------------------|---------------------------|
| GET    | `/api/students`    | Retrieve all students.    |
| POST   | `/api/students`    | Add a new student.        |

#### Example: Add Student
```json
POST /api/students
{
    "name": "John Doe",
    "address": "123 Main Street",
    "subjects": [
        { "id": 1 },
        { "id": 2 }
    ]
}
```

---

## Testing the API
You can test the API using tools like:
- **Postman**
- **cURL**

**Steps**:
1. Authenticate using `/api/auth/login` to get the JWT token.
2. Add the token to the `Authorization` header as `Bearer <token>`.
3. Use the secured endpoints (e.g., `/api/subjects`, `/api/students`).

---

## Default Users
The following users are preloaded in the database:

| Username  | Password        | Role     |
|-----------|-----------------|----------|
| `admin`   | `admin_password`| `ADMIN`  |
| `student` | `student_password` | `STUDENT` |

---

## Security Configuration
- **CSRF** is disabled.
- Stateless sessions are enforced using JWT.
- Endpoints under `/api/auth/**` are open, while others require a valid token.






