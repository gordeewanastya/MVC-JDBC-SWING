CREATE TABLE room_services (
                               room_id INT NOT NULL,
                               service_id INT NOT NULL,
                               CONSTRAINT fk_room_service_room FOREIGN KEY (room_id) REFERENCES rooms(room_id),
                               CONSTRAINT fk_room_service_service FOREIGN KEY (service_id) REFERENCES services(service_id)
);