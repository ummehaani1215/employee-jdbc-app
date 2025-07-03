# Employee JDBC App

A simple Java console application that connects to MySQL using JDBC and performs CRUD operations.

## Features
- Add new employee
- View all employees
- Update salary by ID
- Delete employee by ID

## Technologies Used
- Java
- MySQL
- JDBC
- VS Code

## Mysql workbench 8.0 CE

1. Create database in MySQL:
CREATE DATABASE company_db;
USE company_db;
CREATE TABLE employee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    role VARCHAR(50),
    salary DOUBLE
);
---password is hidden.


## How to compile and run
javac -cp ".;lib/mysql-connector-j-8.x.x.jar" EmployeeApp.java (Compile)

java -cp ".;lib/mysql-connector-j-8.x.x.jar" EmployeeApp (Run)


## Author
Umme Haani - Java Developer Intern

