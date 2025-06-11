# 🥝 Spring Boot CRUD Application with H2, MySQL, and MongoDB
## 📌 Description
This project demonstrates how to implement a CRUD (Create, Read, Update, Delete) REST API using Spring Boot and three different databases:
H2 (In-memory) / MySQL / MongoDB
It follows the MVC architecture and respects REST principles, including the correct use of HTTP methods and response codes
## 🧩 Levels Overview
  🟢 Level 1: CRUD with H2
  🟢 Level 2: CRUD with MySql
  🟢 Level 3: CRUD with MongoDB
  
Create a Spring Boot project with:
Dependencies: 
  - Spring Boot DevTools
  - Spring Web
  - Spring Data JPA
  - H2 Database / MySQL / Mongo


Endpoints:
GET /fruits/getAll
POST /fruits/add
GET /fruits/getOne/{id}
PUT /fruits/update/{id}
DELETE /fruits/delete/{id}

## Bonus Requirements:
Proper use of HTTP verbs and status codes (ResponseEntity)
Centralized exception handling with a GlobalExceptionHandler


## Separation of concerns using layered architecture (Controller, Service, Repository)

Clear API response structure with ResponseEntity
Error handling using a global exception handler
RESTful API design


Java 11+
Spring Boot (latest stable)
Maven 
H2 / MySQL / MongoDB
IntelliJ IDEA / VS Code

🚀 Running the Projects
You can run each level with:
./mvnw spring-boot:run or from your IDE by running the main class.

Make sure to configure database credentials in application.properties for MySQL and MongoDB.

EXAMPLES OF REQUESTS BY COMMAND LINE (BASH) :
CURL (terminal bash)
curl.exe -X GET http://localhost:8080/fruits/getAll
curl.exe -X GET http://localhost:8080/fruits/getOne/2
curl.exe -X DELETE http://localhost:8080/fruits/delete/2
curl.exe -X POST http://localhost:8080/fruits/add -H "Content-Type: application/json" -d  '{"name":"apple","quantityKg":4}'
curl.exe -X POST http://localhost:8080/fruits/add -H "Content-Type: application/json" -d  '{"name":"banana","quantityKg":2}'
curl.exe -X POST http://localhost:8080/fruits/add -H "Content-Type: application/json" -d  '{"name":"cherry","quantityKg":25}'
curl.exe -X PUT http://localhost:8080/fruits/update/1 -H "Content-Type: application/json" -d  '{"name":"peach","quantityKg":3}'
curl.exe -X PUT http://localhost:8080/fruits/update/3 -H "Content-Type: application/json" -d  '{"quantityKg":18}'
curl.exe -X POST http://localhost:8080/fruits/add -H "Content-Type: application/json" -d '{"name":"orange","quantityKg":5}'
