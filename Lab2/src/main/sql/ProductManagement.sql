CREATE DATABASE IF NOT EXISTS ProductManagement;
USE ProductManagement;

DROP TABLE IF EXISTS Product;
CREATE TABLE Product (
     id INT NOT NULL AUTO_INCREMENT,
     name VARCHAR(255) NOT NULL,
     price DECIMAL(10,2) NOT NULL,
     PRIMARY KEY (id)
);

INSERT INTO Product (name, price)
VALUES ('Product 1', 10.6),
       ('Product 2', 2.25),
       ('Product 3', 25);