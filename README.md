# Java Stock Control System 📦🍎

A inventory management system developed in **Java**, designed to demonstrate mastery of Object-Oriented Programming (OOP) and data persistence using semi-structured files (JSON).

## 🚀 Technologies & Architecture

* **Language:** Java 17+
* **Persistence:** JSON integration via the **Google Gson** library
* **Architecture:** Layered Architecture, segregating responsibilities between Entities, Services, and Flow Control
* **Paradigm:** Advanced Object-Oriented Programming (Encapsulation, Inheritance, and Polymorphism)

## 🛠️ Key Features

* **Product Management:** Complete CRUD (Create, Read, Update, Delete) operations for inventory items
* **Data Persistence:** Automatic state saving to `.json` files, ensuring data is retained after the application closes
* **Input Validation:** Robust exception handling to ensure the integrity of prices, quantities, and IDs
* **Custom Serialization:** Efficient use of Gson to map Java objects to structured text and vice versa

## 🏗️ Applied Engineering Concepts

This project was built to validate critical Backend developer competencies:

1. **File I/O Manipulation:** Optimized implementation of disk reading and writing
2. **Exception Handling:** Strategic use of `try-catch` blocks and custom exceptions to prevent system crashes
3. **Collections API:** Use of `List` and `ArrayList` for dynamic in-memory object management
4. **Clean Code:** Small methods, suggestive naming, and clear separation of business logic from persistence logic

## 📖 How to Run

1. Ensure you have JDK 17 or higher and the Gson library in your classpath
2. Clone the repository:
   ```bash
   git clone https://github.com/Hendrick201/Java-Stock-Control-System/tree/main
   ```
3. Import the project into your IDE (IntelliJ/Eclipse) and run the Main class
