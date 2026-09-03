CREATE TABLE favourite_youth_centers (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    youth_center_id BIGINT NOT NULL
);