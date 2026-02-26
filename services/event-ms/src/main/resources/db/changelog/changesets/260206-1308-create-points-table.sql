CREATE TABLE IF NOT EXISTS points
(
    id uuid PRIMARY KEY,
    latitude  DECIMAL(9, 6) NOT NULL,
    longitude DECIMAL(9, 6) NOT NULL,

    UNIQUE(latitude, longitude)
);
