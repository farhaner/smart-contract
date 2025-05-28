# 📊 Database Documentation - Employee Management System

This document provides an overview of the database schema used in the Employee Management System. The database is designed to store and manage data related to employees, departments, and attendance records.

---

## 🏛️ Database Overview

- Database Name: `employee_db`
- Type: Relational Database (Oracle / PostgreSQL / MySQL, etc.)
- Entities:
    - Employees
    - Departments
    - Attendance

---

## 🗂️ Entity Relationship Diagram (ERD)


---

## 🧩 Table Structures

### 1. `Nasabah (Source Of Fund)`

| Column Name | Data Type     | Constraints           | Description            |
|-------------|---------------|-----------------------|------------------------|
| id          | VARCHAR(40)   | PRIMARY KEY, NOT NULL | Unique nasabah ID      |
| nik         | VARCHAR(15)   | UNIQUE, NOT NULL      | Identity of nasabah    |
| full_name   | VARCHAR(100)  | NOT NULL              | Full name of nasabah   |
| eth_balance | BIG DECIMAL   | NOT NULL              | ETH balance of nasabah |
| idr_balance | BIG DECIMAL   | NOT NULL              | IDR balance of nasabah |
|-------------|---------------|-----------------------|------------------------|

---

### 2. `Nasabah (Fund Recipient)`

| Column Name | Data Type     | Constraints           | Description            |
|-------------|---------------|-----------------------|------------------------|
| id          | VARCHAR(40)   | PRIMARY KEY, NOT NULL | Unique nasabah ID      |
| nik         | VARCHAR(15)   | UNIQUE, NOT NULL      | Identity of nasabah    |
| full_name   | VARCHAR(100)  | NOT NULL              | Full name of nasabah   |
| eth_balance | BIG DECIMAL   | NOT NULL              | ETH balance of nasabah |
| idr_balance | BIG DECIMAL   | NOT NULL              | IDR balance of nasabah |
|-------------|---------------|-----------------------|------------------------|
---

### 3. `attendance`

| Column Name     | Data Type     | Constraints           | Description                   |
|-----------------|---------------|------------------------|-------------------------------|
| id              | INTEGER       | PRIMARY KEY, NOT NULL | Attendance record ID          |
| employee_id     | INTEGER       | FOREIGN KEY           | Reference to `employees`      |
| date            | DATE          | NOT NULL              | Date of attendance            |
| status          | VARCHAR(10)   | NOT NULL              | e.g., Present, Absent, Sick   |
| remarks         | TEXT          |                       | Optional notes                |

---

## 🔗 Relationships

- One department can have many employees.
- One employee can have many attendance records.
- `employees.department_id` → `departments.id`
- `attendance.employee_id` → `employees.id`

---

## 📌 Notes

- All date values are stored in UTC.
- Text fields use UTF-8 encoding.
- Consider indexing `email`, `department_id`, and `date` for performance.

---

## 🛠️ Future Enhancements

- Add table for leave requests.
- Implement audit logging table for tracking changes.

---

Created with ❤️ by Muhammad Farhan Erlangga
