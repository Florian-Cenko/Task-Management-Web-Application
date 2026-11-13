# Task Management Web Application

A Full-Stack Task Management web application that allows users to create, view, edit, delete, search, and filter their tasks in real time.

## Technologies Used

### Backend
* **Java 21** & **Spring Boot**
* **Spring Web** (RESTful API)
* **Spring Data JPA** (Hibernate)
* **Spring Security** (HTTP Basic Authentication)
* **Bean Validation** (`jakarta.validation`)
* **PostgreSQL 16** (via Docker)
* **JUnit 5** & **Mockito** (Unit & WebMvc Testing)

### Frontend
* **React** (Vite)
* **Tailwind CSS**
* **React Icons**

## Prerequisites

Before you begin, ensure you have the following installed on your machine:
* **Git**
* **Java JDK 21** (or newer)
* **Maven** (or use the included Maven Wrapper `./mvnw`)
* **Docker** & **Docker Compose** (for the PostgreSQL database)
* **Node.js** (v18+) & **npm**

## Installation & Setup Instructions

### 1. Clone the Repository
Clone the repository to your local machine and navigate into the project folder:
```bash
git clone https://github.com/<your-username>/<your-repository-name>.git
cd <your-repository-name>
```

### 2.Start the Database (PostgreSQL)

The project uses Docker Compose to run a PostgreSQL 16 database.

In the directory containing the docker-compose.yml file, create a .env file with the following configuration:

```bash
POSTGRES_DB=taskdb
POSTGRES_USER=taskmanagementuser
POSTGRES_PASSWORD=your_password
```
Note: Replace your_password with your own database password. The .env file should not be committed to GitHub.

Start the database by running:

docker compose up -d

The PostgreSQL database will be available at localhost:5432.

The database name, username, and password must match the credentials configured in the Spring Boot application.

### 3. Start the Backend
Ensure that Spring Boot's `application.properties` / `application.yml` is configured with the corresponding database credentials. You can start the Backend in two ways:

* **Option A: Via Terminal (Command Line)**
  Navigate to the Backend directory and run:
  ```bash
  ./mvnw spring-boot:run
  ```
  *(On Windows Command Prompt, use `mvnw.cmd spring-boot:run`)*

* **Option B: Via IDE (IntelliJ IDEA, VS Code, Eclipse)**
  1. Open the Backend project in your IDE.
  2. Navigate to `src/main/java/com/example/taskmanagement/TaskmanagementApplication.java`.
  3. Click the **Run (▶)** button next to the `main` method.

The Backend API will be available at: `http://localhost:8080/api/tasks`

### 4. Start the Frontend
Navigate to the Frontend directory, install the dependencies, and start the development server:
```bash
npm install
npm run dev
```
The application will open at: `http://localhost:5173`

## Running Tests

You can execute the Backend Unit Tests (Mockito) and Controller Tests (MockMvc) using either of the following methods:

* **Option A: Via Terminal (Command Line)**
  Run the following command inside the Backend directory:
  ```bash
  ./mvnw test
  ```
  *(On Windows Command Prompt, use `mvnw.cmd test`)*

* **Option B: Via IDE (IntelliJ IDEA, VS Code, Eclipse)**
  1. Navigate to the `src/test/java` directory in your IDE.
  2. Right-click on the `test` folder (or individually on `TaskServiceTest` / `TaskControllerTest`) and select **Run 'All Tests'** (or click the **Run (▶)** icon next to the test class).

## API Endpoints

All endpoints are secured with **Basic Authentication** (`username: admin`, `password: password123`).

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/tasks` | Retrieve all tasks |
| `GET` | `/api/tasks/{id}` | Retrieve a specific task by ID |
| `POST` | `/api/tasks` | Create a new task |
| `PUT` | `/api/tasks/{id}` | Update an existing task |
| `DELETE` | `/api/tasks/{id}` | Delete a task by ID |
| `GET` | `/api/tasks/search?title=...` | Search tasks by title |
| `GET` | `/api/tasks/status?status=...` | Filter tasks by status (`PENDING`, `IN_PROGRESS`, `COMPLETED`) |

## Architectural Choices & Notes

* **Frontend Implementation:** Built with React and Tailwind CSS. As instructed in the assignment guidelines, the Frontend was created entirely using AI tools, focusing on core functionality, responsive design, and a clean user experience (UX).