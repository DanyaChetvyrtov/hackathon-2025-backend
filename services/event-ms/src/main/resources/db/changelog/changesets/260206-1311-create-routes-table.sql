CREATE TABLE IF NOT EXISTS routes
(
    id UUID PRIMARY KEY,
    profile_id UUID,
    created_at TIMESTAMPTZ DEFAULT NOW()
);

CREATE TABLE route_places (
    id UUID PRIMARY KEY,
    route_id UUID NOT NULL REFERENCES routes(id) ON DELETE CASCADE,
    place_id UUID NOT NULL REFERENCES places(id) ON DELETE CASCADE,
    position INT NOT NULL,
    UNIQUE(route_id, position),
    UNIQUE(route_id, place_id)
);

CREATE TABLE route_events (
    id UUID PRIMARY KEY,
    route_id UUID NOT NULL REFERENCES routes(id) ON DELETE CASCADE,
    event_id UUID NOT NULL REFERENCES events(id) ON DELETE CASCADE,
    UNIQUE(route_id, event_id)
);