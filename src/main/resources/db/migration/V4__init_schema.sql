CREATE TABLE user_roles (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    youth_center_id BIGINT NOT NULL,
    role SMALLINT NOT NULL
);