CREATE TABLE employees (
                           employee_id serial PRIMARY KEY,
                           first_name VARCHAR(50) NOT NULL,
                           last_name VARCHAR(50) NOT NULL,
                           position VARCHAR(50) NOT NULL
);