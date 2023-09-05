CREATE TABLE author
(
    id            SERIAL PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    surname       VARCHAR(255) NOT NULL,
    date_of_birth DATE         NOT NULL,
    created_at    DATE         NOT NULL,
    CONSTRAINT uc_author_name_surname UNIQUE (name, surname)
);