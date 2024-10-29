# Basic Calculator Application

This is a simple calculator application built using Java and Spring Boot. 
The application supports basic arithmetic operations such as addition, subtraction, multiplication, and division (10<sup>-6</sup> accuracy).
This application uses for unit testing, test coverage tools, and CI/CD

## Features

- Addition of two numbers
- Subtraction of two numbers
- Multiplication of two numbers
- Division of two numbers

## Technologies Used

- Java
- Spring Boot
- Maven
- Thymeleaf

## Project Structure

- `src/main/java/com/group14/application/calculator/CalculatorService.java`: Contains the service logic for performing calculations.
- `src/main/java/com/group14/application/calculator/CalculatorController.java`: Handles HTTP requests and responses.
- `src/test/java/com/group14/application/operation/AddOperationTest.java`: Contains unit tests for the addition operation.
- ...

## Running the Application in local

1. **Clone the repository:**
   ```sh
   git clone https://github.com/nhanng9642/calculator.git
   cd calculator
   
2. **Build the project using Maven**
    ```sh
   mvn clean install
   
3. **Run the application**
    ```sh
    mvn spring-boot:run
   
4. **Access the application**
    http://localhost:8080/calculator

## Running the Unit Tests
1. **Run the unit tests using Maven**
    ```sh
    mvn clean test

2. **View the test coverage report**
    Open the file `target/site/jacoco/index.html` in a web browser.