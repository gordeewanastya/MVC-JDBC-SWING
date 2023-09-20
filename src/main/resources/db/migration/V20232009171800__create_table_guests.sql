CREATE TABLE guests (
                        guest_id serial PRIMARY KEY,
                        first_name VARCHAR(50) NOT NULL,
                        last_name VARCHAR(50) NOT NULL,
                        email VARCHAR(100),
                        phone_number VARCHAR(20)
);