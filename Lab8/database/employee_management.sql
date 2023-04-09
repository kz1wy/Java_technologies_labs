CREATE DATABASE employee_management;
use employee_management;
CREATE TABLE Employee (
    id INT PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255),
    address VARCHAR(255),
    phone VARCHAR(255)
);

INSERT INTO Employee (id, name, email, address, phone)
VALUES 
    (1, 'Thomas Hardy', 'thomashardy@mail.com', '89 Chiaroscuro Rd, Portland, USA', '(171) 555-2222'),
    (2, 'Dominique Perrier', 'dominiqueperrier@mail.com', 'Obere Str. 57, Berlin, Germany', '(313) 555-5735'),
    (3, 'Maria Anders', 'mariaanders@mail.com', '25, rue Lauriston, Paris, France', '(503) 555-9931'),
    (4, 'Fran Wilson', 'franwilson@mail.com', 'C/ Araquil, 67, Madrid, Spain', '(204) 619-5731'),
    (5, 'Martin Blank', 'martinblank@mail.com', 'Via Monte Bianco 34, Turin, Italy', '(480) 631-2097');