
CREATE DATABASE lab9_10;

USE lab9_10;

CREATE TABLE UserAccounts (
  UserID INT PRIMARY KEY AUTO_INCREMENT,
  Email VARCHAR(255) NOT NULL UNIQUE,
  Password VARCHAR(255) NOT NULL,
  FirstName VARCHAR(255) NOT NULL,
  LastName VARCHAR(255) NOT NULL
);

CREATE TABLE Products (
  ProductID INT PRIMARY KEY AUTO_INCREMENT,
  Code VARCHAR(255),
  ProductName VARCHAR(255) NOT NULL,
  Price DECIMAL(10, 2) NOT NULL,
  Illustration TEXT,
  Description TEXT
);

CREATE TABLE Orders (
  OrderID INT PRIMARY KEY AUTO_INCREMENT,
  OrderNumber VARCHAR(255) NOT NULL UNIQUE,
  TotalSellingPrice DECIMAL(10, 2) NOT NULL,
  ProductList VARCHAR(255)
);

-- Inserting sample data into UserAccounts table
INSERT INTO UserAccounts (Email, Password, FirstName, LastName) VALUES
('john.doe@example.com', 'password123', 'John', 'Doe'),
('jane.doe@example.com', 'password456', 'Jane', 'Doe');

-- Inserting sample data into Products table
INSERT INTO Products (Code, ProductName, Price, Illustration, Description) VALUES
('A001', 'Product A', 19.99, 'https://i0.wp.com/www.flutterbeads.com/wp-content/uploads/2022/01/add-image-in-flutter-hero.png?fit=2850%2C1801&ssl=1', 'Description of Product A'),
('B002', 'Product B', 29.99, 'https://i0.wp.com/www.flutterbeads.com/wp-content/uploads/2021/11/set-background-image-flutter-hero.jpeg?fit=1920%2C1280&ssl=1', 'Description of Product B');

-- Inserting sample data into Orders table
INSERT INTO Orders (OrderNumber, TotalSellingPrice, ProductList)
VALUES ('ORD-001', 39.98, 'A001,B002'),
		('ORD-002', 19.99, 'A001'),
		('ORD-003', 49.98, 'B002,A001'),
		('ORD-004', 59.97, 'A001,B002,A001');