CREATE TABLE reservations (
                              reservation_id serial PRIMARY KEY,
                              room_id INT NOT NULL,
                              guest_id INT NOT NULL,
                              check_in_date DATE,
                              check_out_date DATE,
                              CONSTRAINT fk_room FOREIGN KEY (room_id) REFERENCES rooms(room_id),
                              CONSTRAINT fk_guest FOREIGN KEY (guest_id) REFERENCES guests(guest_id)
);