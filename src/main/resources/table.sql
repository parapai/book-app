DROP TABLE IF EXISTS reservations;
DROP TABLE IF EXISTS concerts;

CREATE TABLE concerts (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    event_date TIMESTAMP NOT NULL,
    booking_start TIME NOT NULL,
    booking_end TIME NOT NULL,
    total_tickets INTEGER NOT NULL,
    available_tickets INTEGER NOT NULL
);

CREATE INDEX idx_concert_name ON concerts(name);


CREATE TABLE reservations (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    concert_id UUID NOT NULL,
    user_id VARCHAR(50) NOT NULL,
    reservation_time TIMESTAMP NOT NULL,
    CONSTRAINT fk_concert FOREIGN KEY (concert_id) REFERENCES concerts(id)
);


INSERT INTO concerts (name, event_date, booking_start, booking_end, total_tickets, available_tickets)
VALUES (
    'Konser Akbar Nassar Kiyowo', 
    '2026-04-21 19:00:00', 
    '23:00:00', 
    '23:20:00', 
    2000, 
    2000
);