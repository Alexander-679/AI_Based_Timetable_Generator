AI-Based Timetable Generator

An AI-based timetable generator built using Java Spring Boot, MySQL, and HTML/CSS/JavaScript.
The application allows users to enter academic details through a web interface and automatically generates a conflict-free timetable using rule-based AI logic. Users can also download the generated timetable as a PDF.

1. Project Overview

Creating timetables manually is time-consuming and error-prone. This project solves that problem by automatically generating timetables based on user input such as subjects, teachers, rooms, and time slots while ensuring that all constraints are satisfied.

The system uses constraint-based AI logic (not machine learning) to ensure:

No teacher is assigned to two classes at the same time

No room is double-booked

Break times are respected

Subjects are scheduled according to their required weekly sessions

2. Features

User-friendly web interface for entering timetable data

Automatic conflict-free timetable generation

Rule-based AI logic for scheduling

Persistent data storage using MySQL

Download generated timetable as a PDF

Modern UI with validation, loading indicator, dark/light mode, and responsive design

3. Tech Stack
Backend

Java 17+

Spring Boot

Spring Data JPA

Hibernate

MySQL

OpenPDF (for PDF generation)

Frontend

HTML

CSS

JavaScript (Fetch API)

4. AI Logic Used

This project uses rule-based / constraint-based AI, which is widely used in real-world scheduling systems.

Constraints Applied:

A teacher cannot teach more than one class at the same time

A room cannot be allocated to multiple classes at the same time

Break time slots are excluded from scheduling

Each subject is scheduled according to its required sessions per week

The algorithm follows a greedy scheduling approach, selecting the first valid time slot that satisfies all constraints.


5. Project Architecture
Frontend (HTML/CSS/JS)
        ↓
REST Controller (Spring Boot)
        ↓
Service Layer (AI Logic)
        ↓
Repository Layer (JPA)
        ↓
MySQL Database


6. Database Design
Tables

subject

teacher

room

time_slot

timetable

Hibernate automatically manages table creation and relationships using JPA annotations.

7. REST API Endpoints
Method	Endpoint	Description
POST	/api/timetable/input	Save user input & generate timetable
GET	/api/timetable	Fetch generated timetable
GET	/api/timetable/download	Download timetable as PDF


8. User Interface Features

Clean card-based layout

Input validation with error messages

Loading spinner during timetable generation

Dark / Light mode toggle

Mobile responsive design

Institute name and branding

One-click PDF download

▶️ How to Run the Project
1️⃣ Clone the Repository
git clone https://github.com/your-username/ai-timetable-generator.git

2️⃣ Configure MySQL

Create a database:

CREATE DATABASE ai_timetable_db;


Update application.properties:

spring.datasource.url=jdbc:mysql://localhost:3306/ai_timetable_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

3️⃣ Run the Application

Run the Spring Boot application:

mvn spring-boot:run

4️⃣ Open in Browser
http://localhost:8080/timetable.html

📄 PDF Export

Users can download the generated timetable as a PDF.
The PDF is dynamically generated on the backend using OpenPDF and includes:

Title

Timetable table (Day, Time, Subject, Teacher, Room)

Sample Input Example

Subjects

Math:4, Physics:3, English:2


Teachers

Mr. Rao:Math, Ms. Priya:Physics, Mrs. Anjali:English


Rooms

Room A, Room B


Time Slots

Monday 09:00-10:00,
Monday 10:00-11:00,
Monday 11:00-12:00,
Monday 12:00-13:00 (Break),
Tuesday 09:00-10:00,
Tuesday 10:00-11:00

10. Key Learnings

Designing RESTful APIs using Spring Boot

Applying rule-based AI logic for real-world problems

Using Spring Data JPA to persist user input without SQL

Handling transactions safely using @Transactional

Frontend–backend integration using Fetch API

Generating PDFs dynamically from backend data

11. Future Enhancements

Multiple classes / departments support

Teacher availability constraints

Timetable optimization for balanced distribution

Export as Excel

Authentication and role-based access

Author

Sandrapati Alexander
Java Backend Developer (Fresher)
Passionate about backend development, system design, and building real-world applications using Java and Spring Boot.
