CREATE DATABASE spring_commerce;
USE spring_commerce;

CREATE TABLE products (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    price DECIMAL(10,2),
    category VARCHAR(50),
    brand VARCHAR(50),
    color VARCHAR(50)
);

CREATE TABLE orders (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(100),
    customer_address VARCHAR(200),
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE order_items (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    order_id INT UNSIGNED NOT NULL,
    product_id INT UNSIGNED NOT NULL,
    quantity INT,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

CREATE TABLE carts (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    customer_id INT UNSIGNED NOT NULL, -- assume we have a customer table
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE cart_items (
    id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    cart_id INT UNSIGNED NOT NULL,
    product_id INT UNSIGNED NOT NULL,
    quantity INT,
    FOREIGN KEY (cart_id) REFERENCES carts(id),
    FOREIGN KEY (product_id) REFERENCES products(id)
);

INSERT INTO products (name, price, category, brand, color) VALUES
('T-Shirt', 19.99, 'Clothing', 'Nike', 'Black'),
('Running shoe', 79.99, 'Footwear', 'Adidas', 'White'),
('Wireless Headphones', 129.99, 'Electronics', 'Sony', 'Red'),
('Hiking Backpack', 59.99, 'Outdoor Gear', 'North Face', 'Green'),
('Yoga Mat', 24.99, 'Fitness', 'Lululemon', 'Purple');

INSERT INTO orders (customer_name, customer_address) VALUES
('John Smith', '123 Main St'),
('Jane Doe', '456 Oak Ave');

INSERT INTO order_items (order_id, product_id, quantity) VALUES
(1, 2, 1),
(1, 4, 2),
(2, 1, 3),
(2, 3, 1);

INSERT INTO carts (customer_id) VALUES
(1),
(2);

INSERT INTO cart_items (cart_id, product_id, quantity) VALUES
(1, 1, 2),
(1, 3, 1),
(2, 2, 1),
(2, 4, 1),
(2, 5, 2);
