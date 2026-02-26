CREATE TABLE IF NOT EXISTS events
(
    id              UUID PRIMARY KEY,
    title           VARCHAR(255) NOT NULL,
    type            VARCHAR(100)   NOT NULL,
    event_start     TIMESTAMPTZ NOT NULL,
    event_end       TIMESTAMPTZ NOT NULL,
    price           DECIMAL(10, 2),
    age_restriction INTEGER DEFAULT 0,
    place_id        UUID NOT NULL,
    FOREIGN KEY (place_id) REFERENCES places (id)
);