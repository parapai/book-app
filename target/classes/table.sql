DROP TABLE IF EXISTS reservations;
DROP TABLE IF EXISTS concerts;

CREATE TABLE concerts (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    event_date TIMESTAMP NOT NULL,
    booking_start TIME NOT NULL,
    booking_end TIME NOT NULL,
    total_tickets INTEGER NOT NULL,
    available_tickets INTEGER NOT NULL
);

CREATE INDEX idx_concert_name ON concerts(name);


CREATE TABLE reservations (
    id VARCHAR(36) PRIMARY KEY,
    concert_id VARCHAR(36) NOT NULL,
    user_id VARCHAR(50) NOT NULL,
    reservation_time TIMESTAMP NOT NULL,
    CONSTRAINT fk_concert FOREIGN KEY (concert_id) REFERENCES concerts(id)
);


INSERT INTO concerts (id, name, event_date, booking_start, booking_end, total_tickets, available_tickets)
VALUES (
    gen_random_uuid()::varchar, 
    'Konser Akbar Nassar Kiyowo', 
    '2026-04-21 19:00:00', 
    '12:00:00', 
    '14:20:00', 
    2000, 
    2000
);