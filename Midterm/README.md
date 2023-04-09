IMPORTANT:
When you access to http://localhost:8080 (details below), you need to log in to spring security, I curently set account:
username: admin
password: 123
for security, remove username and password in application.properties
---------------------------------------------------------------------------------------------------------------------------------

A brief explanation for software development principles, patterns and practices being applied:
This project applies several software development principles, patterns, and practices, such as:

Model-View-Controller (MVC) architecture: The MVC architecture divides the application into three components: Model (data and business logic), View (presentation layer), and Controller (intermediary between Model and View).

Dependency Injection: The Spring Framework injects dependencies into the classes using its dependency injection mechanism.

Repository pattern: The repository pattern isolates the data access code from the rest of the application.

Service layer: The service layer encapsulates the business logic of the application and provides an abstraction layer between the controller and the repository.

RESTful API design: The API follows the RESTful design principles, where the HTTP methods perform CRUD operations on the resources.

Unit testing: JUnit, a popular Java testing framework, tests the application thoroughly to ensure that all components work as expected.

Database design: The entity-relationship diagram (ERD) designs a normalized and efficient database schema.


These principles, patterns, and practices make the application modular, scalable, maintainable, and testable.

--------------------------------------------------------------------------------------------------------------------------
Code Structure:

The Spring Boot application consists of several packages:

com.springcommerce.model: contains the entity classes representing the data objects used in the application, such as Product, Category, Cart, CartItem, Order, and OrderItem.

com.springcommerce.repository: contains the repositories for each entity class, which are responsible for performing CRUD (Create, Read, Update, Delete) operations on the database.

com.springcommerce.service: contains the service classes, which handle the business logic of the application and make use of the repositories to interact with the database.

com.springcommerce.controller: contains the controller classes, which receive HTTP requests from the client and delegate the request to the appropriate service class to handle it. The controller classes also return the HTTP response to the client.

com.springcommerce.exception: contains custom exception classes to handle various exceptions that may occur during the application's execution.

com.springcommerce.util: contains utility classes used throughout the application.

resources: contains the application.properties file, which configures the application, and the data.sql file, which initializes the database with some sample data.

Using MockitoJUnitRunner to Test
---------------------------------------------------------------------------------------------------------------------------------
Steps to run the application:

Clone the project from the repository.

Install MySQL or Postgres DBMS on your local computer (or docker).

Run spring_commerce.sql to create database

Open the application.properties file in the resources folder and configure the database connection properties.

Open a command prompt or terminal and navigate to the project directory.

Run the following command to build the application:

mvn clean install

Run the following command to start the application:

java -jar target/spring-commerce-0.0.1-SNAPSHOT.jar

Or if you are using IDE such as Intellij, just press Run button

Run the following command to test the application:

mvn test

Open a web browser and navigate to http://localhost:8080 to access the application, it will redict you to homepage, just try to explore in products tab.


Note: Make sure that port 8080 is not already in use by another application. If it is, you can change the port number in the application.properties file.
--------------------------------------------------------------------------------------------------------------------------
Full CURL commands or Postman snapshots to verify the APIs
(include full request endpoints, HTTP Headers and request payload
if any) in documents/API results folder.