CREATE DATABASE IF NOT EXISTS erasm_db;

USE erasm_db;

CREATE TABLE roles (
    roleid BIGINT AUTO_INCREMENT PRIMARY KEY,
    role_name VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE users (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) UNIQUE NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL,
    role_id BIGINT,
    FOREIGN KEY (role_id) REFERENCES roles(roleid)
);

CREATE TABLE employees (
    employee_id BIGINT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE skills (
    skill_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    skill_name VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE employee_skills (
    employee_skill_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    employee_id BIGINT,
    skill_id BIGINT,
    skill_level VARCHAR(50),
    experience_years INT,
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id),
    FOREIGN KEY (skill_id) REFERENCES skills(skill_id)
);

CREATE TABLE certifications (
    certification_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    employee_id BIGINT,
    certification_name VARCHAR(200),
    issuing_organization VARCHAR(200),
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id)
);

CREATE TABLE projects (
    project_id BIGINT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE resource_requests (
    request_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    required_skill VARCHAR(100),
    required_count INT,
    status VARCHAR(50),
    project_id BIGINT,
    FOREIGN KEY (project_id) REFERENCES projects(project_id)
);

CREATE TABLE allocations (
    allocation_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    allocation_percentage INT,
    allocation_date DATE,
    release_date DATE,
    employee_id BIGINT,
    project_id BIGINT,
    FOREIGN KEY (employee_id) REFERENCES employees(employee_id),
    FOREIGN KEY (project_id) REFERENCES projects(project_id)
);

CREATE TABLE audit_logs (
    audit_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    action VARCHAR(255),
    performed_by VARCHAR(100),
    action_time DATETIME
);