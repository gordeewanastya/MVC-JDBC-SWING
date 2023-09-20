CREATE TABLE hotels (
                        hotel_id serial PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        address VARCHAR(255) NOT NULL,
                        star_rating INT
);