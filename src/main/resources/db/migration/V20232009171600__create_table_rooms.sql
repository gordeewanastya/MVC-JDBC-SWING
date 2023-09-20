CREATE TABLE rooms (
                       room_id serial PRIMARY KEY,
                       hotel_id INT NOT NULL,
                       room_number VARCHAR(10) NOT NULL,
                       room_type VARCHAR(50),
                       rate NUMERIC(3, 2),
                       CONSTRAINT fk_hotel FOREIGN KEY (hotel_id) REFERENCES hotels(hotel_id)
);