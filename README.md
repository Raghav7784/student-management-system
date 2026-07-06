Enterprise Resource Allocation & Skill Management System (ERASM)

ERASM is a centralized Spring Boot platform designed to help IT service organizations manage employees, skills, projects, and resource allocation efficiently. It solves common project management pain points such as employees remaining on the bench without allocation, lack of visibility into employee competencies, manual allocation processes, and the absence of a centralized skill inventory or approval workflow.

The application is built using Core Java, Spring Boot, Hibernate/JPA, and Spring Security (JWT), following layered architecture and industry-standard development practices.


Table of Contents


Problem Statement
Technology Stack
User Roles
Modules & Features
Project Structure
Database Design
JPA Relationships
API Endpoints
Security
Logging
Exception Handling
Testing
Non-Functional Requirements
Git Workflow
Prerequisites
Installation
Deliverables
Evaluation Rubric
License



Problem Statement

In IT service companies, project managers frequently face challenges in finding the right resources with appropriate skills and availability. Common problems include:


Employees remaining on the bench without allocation
Difficulty identifying employees with required skills
Lack of visibility into employee competencies
Manual resource allocation process
No centralized skill inventory
Lack of utilization tracking
No approval workflow for resource allocation



Technology Stack

CategoryTechnologyLanguageCore JavaFrameworkSpring BootORMHibernate / JPASecuritySpring Security, JWT, BCryptDatabaseRelational DB (MySQL / PostgreSQL)TestingJUnit 5, MockitoLoggingSLF4J + LogbackBuild ToolMavenAPI TestingPostmanVersion ControlGit & GitHub


User Roles

1. Admin


Manage Users
Manage Roles
Manage Skills
View Reports
Monitor Activities


2. Delivery Manager


Create Projects
Raise Resource Requests
Review Allocations
Monitor Utilization


3. Resource Manager


Allocate Resources
Approve Requests
Reject Requests
View Resource Availability


4. Employee


Update Profile
Add Skills
Add Certifications
View Assignments


5. Auditor


View Audit Logs
Generate Reports
Track Activities



Modules & Features

Module 1: User Management


Register / Login / Update / Delete User
Change Password
Assign Roles


Validations: Email must be unique · Password minimum 8 characters · Role mandatory

Module 2: Skill Management


Add / Update / Delete Skill
View Skill List


Examples: Java, Spring Boot, React, Angular, AWS, Azure

Module 3: Employee Skill Profile


Add Skill
Update Skill Level
Add Experience
Add Certifications


SkillLevelJavaAdvancedSpring BootIntermediateReactBeginner

Module 4: Project Management


Create / Update / Close Project
Assign Technologies


Project details tracked: Project Name, Client Name, Start Date, End Date, Technology Stack, Budget

Module 5: Resource Request Management

Managers can raise resource requests specifying required skills and headcount.

Example — Project: Healthcare Portal


Required Skills: Java, Spring Boot, React
Java Developers: 3
React Developers: 2


Module 6: Approval Workflow

Draft
  ↓
Submitted
  ↓
Resource Manager Review
  ↓
Approved
  ↓
Allocated
  ↓
Completed

Status is persisted in the database at every stage.

Module 7: Resource Allocation


Allocate / Reallocate / Release Employee


Validation: An employee should not exceed 100% allocation.

ScenarioAllocationResultProject A 60% + Project B 40%100%AllowedProject A 70% + Project B 50%120%Not Allowed

Module 8: Utilization Dashboard

Billable %: (Billable Hours / Total Hours) × 100

Bench %: (Bench Hours / Total Hours) × 100

Module 9: Audit Management

Tracks Created By, Modified By, Created Date, Modified Date for every critical action.

Module 10: Reports


Skill Report — employees grouped by skill
Utilization Report — employee utilization percentage
Project Allocation Report — employees assigned to projects



Project Structure

src/
├── main/
│   ├── java/com/example/demo/
│   │   ├── controller/
│   │   ├── service/
│   │   │   └── impl/
│   │   ├── repository/
│   │   ├── entity/
│   │   ├── dto/
│   │   ├── mapper/
│   │   ├── security/
│   │   ├── exception/
│   │   ├── config/
│   │   └── DemoApplication.java
│   └── resources/
│       ├── application.properties
│       └── static/
└── test/
    └── java/com/example/demo/service/
        ├── EmployeeServiceImplTest.java
        ├── ProjectServiceImplTest.java
        ├── AllocationServiceImplTest.java
        ├── ResourceRequestServiceImplTest.java
        ├── DashboardServiceImplTest.java
        ├── AttendanceServiceImplTest.java
        ├── LeaveServiceImplTest.java
        └── PayrollServiceImplTest.java


Database Design

Tables


users
roles
employees
skills
employee_skills
certifications
projects
resource_requests
allocations
audit_logs



JPA Relationships

RelationshipMappingOne-To-OneUser ↔ EmployeeOne-To-ManyProject → ResourceRequestMany-To-OneEmployee → RoleMany-To-ManyEmployee ↔ Skill


API Endpoints

Authentication APIs

POST /auth/register
POST /auth/login
POST /auth/logout

Employee APIs

GET    /employees
GET    /employees/{id}
POST   /employees
PUT    /employees/{id}
DELETE /employees/{id}

Project APIs

GET    /projects
POST   /projects
PUT    /projects/{id}
DELETE /projects/{id}

Allocation APIs

POST /allocations
PUT  /allocations/{id}
GET  /allocations


Security

Mandatory security measures implemented:


Spring Security
JWT based authentication
BCrypt password encryption
Role-Based Access Control (RBAC)


Method-level security example:

java@PreAuthorize("hasRole('ADMIN')")
@PreAuthorize("hasRole('MANAGER')")


Logging

Implemented using SLF4J + Logback.

LevelEventsINFOUser Login, Project Creation, Resource AllocationWARNInvalid Request, Unauthorized AccessERRORSystem Exception, Database Failure

Never logged: Password, JWT Token, Personal Information


Exception Handling


Global Exception Handler using @RestControllerAdvice


Custom Exceptions:


UserNotFoundException
ProjectNotFoundException
SkillNotFoundException
AllocationException



Testing

Testing is implemented using JUnit 5 and Mockito, targeting a minimum of 80% code coverage.

Covered service classes:


Employee Service
Project Service
Allocation Service
Resource Request Service
Dashboard Service
Attendance Service
Leave Service
Payroll Service



Latest run: 59/59 tests passed — 0 errors, 0 failures (see Test Report deliverable for the full JUnit execution report).




Non-Functional Requirements

CategoryRequirementSecurityJWT Authentication, Password Encryption, RBACPerformanceAPI response time under 2 secondsScalabilitySupport 1000+ usersReliabilityCentralized error handling and loggingMaintainabilityLayered architecture, reusable components


Git Workflow

Branch Structure

main
develop
feature/*
release/*
hotfix/*

Commit Message Format

feat: add login functionality
fix: resolve JWT validation issue
refactor: improve allocation logic


Prerequisites

Before running the project, ensure you have installed:


Java 17 or later
Maven 3.8 or later
MySQL / PostgreSQL (or your configured RDBMS)



Installation

Clone the repository:

bashgit clone https://github.com/<your-username>/erasm-platform.git

Navigate to the project directory:

bashcd erasm-platform

Configure the database connection in src/main/resources/application.properties.

Build the project:

bashmvn clean install

Run the application:

bashmvn spring-boot:run

Run the test suite:

bashmvn test


Deliverables


Source Code (GitHub Repository Link)
Documentation (Project Overview, Problem Statement, Database Design, ER Diagram, API List, Screenshots, Test Cases)
SQL Script (Database Creation Script)
Postman Collection (all APIs exported)
Test Report (JUnit Test Execution Report)



Evaluation Rubric

CriteriaMarksJava Coding Standards10OOP Concepts10Database Design10Hibernate/JPA10Spring Boot APIs15Security15Exception Handling5Logging5Testing10Git Usage5Documentation5Total100


License

This project is developed for educational, demonstration, and portfolio purposes.
