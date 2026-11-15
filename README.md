# Task Management Web Application

A full-stack Task Management web application that allows users to create, view, edit, delete, search, and filter tasks.

## Technologies Used

### Backend
- **Java 21** & **Spring Boot**
- **Spring Web** – RESTful API
- **Spring Data JPA** – Hibernate
- **Spring Security** – HTTP Basic Authentication
- **Bean Validation** – Jakarta Validation
- **PostgreSQL 16** – Database
- **Docker & Docker Compose** – Database containerization
- **JUnit 5 & Mockito** – Unit and controller testing

### Frontend
- **React** (Vite)
- **Tailwind CSS**
- **React Icons**

## Prerequisites

Make sure you have the following installed:

- Git
- Java JDK 21 or newer
- Docker Desktop (including Docker Compose)
- Node.js (v18 or newer) and npm
- Maven (optional, since the project includes the Maven Wrapper)

## Installation & Setup

### 1. Clone the Repository

Clone the repository and navigate to the project directory:

```bash
git clone https://github.com/<your-username>/<your-repository-name>.git
cd <your-repository-name>
```

### 2. Configure Environment Variables

The project uses environment variables to keep database credentials out of the source code.

In the root directory of the project (the same directory as `docker-compose.yml`), create a file named `.env`.

Add the following configuration:

```env
POSTGRES_DB=taskdb
POSTGRES_USER=taskmanagementuser
POSTGRES_PASSWORD=your_password
DB_PASSWORD=your_password
```

Replace `your_password` with a password of your choice. Use the same password for both `POSTGRES_PASSWORD` and `DB_PASSWORD`.

**Important:**
- The `.env` file is used by Docker Compose to configure the PostgreSQL container.
- Spring Boot does not automatically load the root `.env` file. The `DB_PASSWORD` environment variable must also be provided when starting the backend.
- Never commit your actual `.env` file to GitHub. It is included in `.gitignore`.
- The repository includes an `.env.example` file as a template.

### 3. Start the PostgreSQL Database

Make sure Docker Desktop is running.

From the root project directory, execute:

```bash
docker compose up -d
```

This starts the PostgreSQL 16 container.

### 4. Start the Backend

Navigate to the backend directory:

```bash
cd backend
```

The Spring Boot application uses the following environment variables:

- `DB_URL` – Database connection URL (optional, defaults to `jdbc:postgresql://localhost:5432/taskdb`)
- `DB_USERNAME` – Database username (optional, defaults to `taskmanagementuser`)
- `DB_PASSWORD` – Database password (required)

You must provide `DB_PASSWORD` before starting the application.

**Option A: Windows PowerShell**

Run the following commands from the `backend` directory:

```powershell
$env:DB_PASSWORD="your_password"
.\mvnw.cmd spring-boot:run
```

**Option B: macOS / Linux**

```bash
export DB_PASSWORD=your_password
./mvnw spring-boot:run
```

**Option C: Using an IDE (IntelliJ IDEA, Eclipse, VS Code)**

1. Open the backend project in your IDE.
2. Open the Run Configuration for `TaskmanagementApplication`.
3. Add the following environment variable:

   ```text
   DB_PASSWORD=your_password
   ```

4. Save the configuration and run `TaskmanagementApplication`.

The Backend API will be available at:

`http://localhost:8080/api/tasks`

### 5. Start the Frontend

Open a new terminal and navigate to the frontend directory:

```bash
cd frontend
```

Install the dependencies:

```bash
npm install
```

Start the development server:

```bash
npm run dev
```

The frontend will be available at:

`http://localhost:5173`

## Running Tests

Backend tests can be executed from the `backend` directory.

**Option A: Windows**

```powershell
.\mvnw.cmd test
```

**Option B: macOS / Linux**

```bash
./mvnw test
```

Alternatively, you can run the tests directly from your IDE by navigating to `src/test/java` and selecting **Run All Tests**.

## API Endpoints

All API endpoints are secured using HTTP Basic Authentication.

Default development credentials:

- Username: `admin`
- Password: `password123`

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/tasks` | Retrieve all tasks |
| GET | `/api/tasks/{id}` | Retrieve a specific task by ID |
| POST | `/api/tasks` | Create a new task |
| PUT | `/api/tasks/{id}` | Update an existing task |
| DELETE | `/api/tasks/{id}` | Delete a task by ID |
| GET | `/api/tasks/search?title=...` | Search tasks by title |
| GET | `/api/tasks/status?status=...` | Filter tasks by status |

Available task statuses:

- `PENDING`
- `IN_PROGRESS`
- `COMPLETED`

## Architectural Choices & Notes

- **Backend:** Built with Java and Spring Boot, following a layered architecture with controllers, services, and repositories.
- **Database:** PostgreSQL is used for persistent data storage, with Docker Compose providing a consistent database environment.
- **Environment Variables:** Database credentials are externalized to avoid exposing sensitive configuration in the source code.
- **Frontend:** Built with React and Tailwind CSS. As specified in the assignment guidelines, the frontend was developed entirely using AI tools, with a focus on core functionality, responsive design, and a clean user experience (UX).
- **Testing:** JUnit 5, Mockito, and MockMvc are used to test backend functionality and REST API endpoints.
