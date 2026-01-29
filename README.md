# AI-Based Timetable Generation System

This is a full-stack timetable scheduling application built using Java, Spring Boot, MySQL, HTML, CSS, and JavaScript. The system accepts academic input details and generates a conflict-free timetable using structured AI logic and constraint rules.

---

## Overview

Manual timetable creation is error-prone and time-consuming. This system automates schedule generation while ensuring:
- No teacher is booked more than once at the same time
- No room is double-booked
- Break times are respected
- Subject session requirements are met

This project demonstrates backend and frontend integration, REST APIs, rule-based logic, and PDF generation.

---

## Tech Stack

- **Backend:** Java, Spring Boot  
- **Database:** MySQL  
- **Frontend:** HTML, CSS, JavaScript  
- **PDF Generation:** OpenPDF  
- **Build:** Maven  
- **API Testing:** Postman

---

## Features

- Accepts timetable input via web interface  
- Generates conflict-free schedule using rule-based logic  
- Downloads generated timetable as a PDF  
- Frontend validation and responsive layout  
- API endpoints for generation and retrieval

---

## Project Structure

src/
├── controller/ # REST API endpoints
├── service/ # Scheduling logic
├── repository/ # Database access
├── model/ # Entity classes
├── resources/
└── static/ # Frontend files (HTML, CSS, JS)

yaml
Copy code

---

## API Endpoints

| Method | Endpoint | Description |
|-------|----------|-------------|
| POST   | `/api/timetable/input` | Save input & generate timetable |
| GET    | `/api/timetable`       | Fetch generated timetable |
| GET    | `/api/timetable/download` | Download PDF of timetable |

### Sample API Example

POST /api/timetable/input
Content-Type: application/json

{
"subjects": [...],
"teachers": [...],
"rooms": [...],
"timeSlots": [...]
}

yaml
Copy code

Use Postman to test endpoints.

---

## How to Run

1. Clone the repository:
```bash
git clone https://github.com/Alexander-679/AI_Based_Timetable_Generator.git
Install and configure MySQL:

Create database:

sql
Copy code
CREATE DATABASE timetable_db;
Update application.properties:

ini
Copy code
spring.datasource.url=jdbc:mysql://localhost:3306/timetable_db
spring.datasource.username=root
spring.datasource.password=your_password
Build and run the application:

bash
Copy code
mvn clean install
mvn spring-boot:run
Open browser and navigate to:

bash
Copy code
http://localhost:8080/timetable.html
Key Learnings
Designed RESTful APIs using Spring Boot

Applied rule-based scheduling logic

Integrated frontend and backend workflows

Generated downloadable PDF reports

Used Spring Data JPA and MySQL

Future Enhancements
Add authentication and role-based access

Support multi-department scheduling

Add unit and integration tests

Optimize scheduling algorithm for performance

Author
Sandrapati Alexander
Final-year B.Tech student | Backend Developer
