-- liquibase formatted sql

-- changeset dasemenov:260206-1309-create-places-table
CREATE TABLE IF NOT EXISTS places
(
    id        UUID PRIMARY KEY,
    title     VARCHAR(255) NOT NULL,
    type      VARCHAR(100) NOT NULL,
    point_id  UUID NOT NULL REFERENCES points(id)
);
-- rollback DROP TABLE places;