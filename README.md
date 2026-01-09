🐾 Pet Shop Management System

A console-based Java application built using Core Java, JDBC, and MySQL.
This project helps manage a simple pet shop where users can register, log in, view pets, and purchase pets with confirmation.

📌 Beginner-friendly project designed to understand JDBC, database connectivity, and console-based application flow.

📌 Features

✔ User Registration
✔ User Login
✔ Add Pets (Dog & Cat)
✔ View Available Pets
✔ Buy Pet with Purchase Confirmation
✔ MySQL Database Connectivity using JDBC
✔ Uses PreparedStatement (SQL Injection safe)

🛠️ Technologies Used
Technology	Purpose
Java (JDK 8+)	Application logic
JDBC	Database connectivity
MySQL	Data storage
Git & GitHub	Version control
Eclipse / IntelliJ / VS Code	IDE
📂 Project Structure
Pet-Shop-Management-System/
│
├── src/
│   └── in/
│       └── sp/
│           └── pet/
│               └── PetManagementSystem.java
│
├── README.md

🗄️ Database Configuration
1️⃣ Create Database
CREATE DATABASE petshop_db;
USE petshop_db;

2️⃣ Users Table
CREATE TABLE users (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50)
);

3️⃣ Pets Table
CREATE TABLE pets (
    id INT PRIMARY KEY,
    type VARCHAR(20),
    name VARCHAR(50),
    price DOUBLE
);

⚙️ Database Connection Setup

Update the database credentials in
PetManagementSystem.java:

DriverManager.getConnection(
    "jdbc:mysql://localhost:3306/petshop_db",
    "root",
    "root"
);


🔹 Replace username and password according to your MySQL setup.

🔌 Add MySQL JDBC Driver

Download MySQL Connector/J

Add it to your project Build Path

Restart the IDE if required

▶️ How to Run the Application

Import the project into Eclipse / IntelliJ / VS Code

Ensure MySQL is running

Run:

PetManagementSystem.java

📋 Sample Menu
==== PET SHOP ====
1. Register
2. Login
3. Exit

🔐 Security Note

⚠ Passwords are stored in plain text
(This is for learning purposes only)

✔ In real-world applications:

Use password hashing

Implement authentication & authorization

🚀 Future Enhancements

Password Encryption

Admin & Customer Roles

Purchase History

GUI using JavaFX / Swing

Web version using Spring Boot

👨‍💻 Author

Swapnil Ghodekar
Java Developer | JDBC | MySQL

🔗 GitHub:
👉 https://github.com/swapnilghodekar0123
