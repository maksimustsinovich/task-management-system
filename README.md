# Task Management System

### Endpoints

* **Authentication**
    * `POST /api/auth/register` - `All` - register new user
    * `POST /api/auth/login` - `All` - login as existing user 
    * `POST /api/auth/refresh` - `Authorized` - refresh access token
* **User**
    * `GET /api/users` - `Authorized` - get all users
    * `GET /api/users/{id}` - `Authorized` - get user by ID
    * `PUT /api/users/{id}` - `Admin (all users), authorized (yourself)` - update user
    * `DELETE /api/users/{id}` - `Admin` - delete user
    * `GET /api/users/{id}/initiated` - `Authorized` - get all task initiated by user
    * `GET /api/users/{id}/executed` - `Authorized` - get all task executed by user
* **Task**
    * `POST /api/tasks` - `Authorized` - create a new task
    * `GET /api/tasks` - `Authorized` - get all tasks
    * `GET /api/tasks/{id}` - `Authorized` - get task by ID
    * `PUT /api/tasks/{id}` - `Admin (all tasks), authorized (only own)` - update task
    * `DELETE /api/tasks/{id}` - `Admin (all tasks), authorized (only own)` - delete task
    * `GET /api/tasks/{id}/comments` - `Authorized` - get all comments for a task
    * `POST /api/tasks/{id}/comments` - `Authorized` - create a new comment for a task
    * `POST /api/tasks/{id}/executor` - `Admin (all tasks), authorized (only own)` - set executor
    * `POST /api/tasks/{id}/status` - `Admin (all tasks), authorized (own or executed)` - set status
* **Comment**
    * `GET /api/comments` - `Authorized` - get all comments
    * `GET /api/comments/{id}` - `Authorized` - get comment by ID
    * `PUT /api/comments/{id}` - `Admin (all comments), authorized (only own)` - update comment
    * `DELETE /api/comments/{id}` - `Admin` - delete comment

### Used technologies

* Java 17
* Spring Boot
* Spring Data JPA
* Spring Web
* Spring Security
* Swagger
* SpringDoc
* JWT
* Lombok
* MapStruct
* Docker
* PostgreSQL

### How to run

* Clone the repository
* Setting up (application.yml)
* Run the application: `mvn spring-boot:run`