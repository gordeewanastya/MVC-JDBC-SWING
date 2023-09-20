CREATE TABLE services (
                          service_id serial PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          description VARCHAR(150),
                          price NUMERIC(10, 2)
);