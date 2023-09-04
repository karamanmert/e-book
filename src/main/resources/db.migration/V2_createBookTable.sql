CREATE TABLE book
(
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(100) NOT NULL,
    release_date DATE         NOT NULL,
    edition      INT          NOT NULL,
    type         VARCHAR(50)  NOT NULL,
    language     VARCHAR(3)   NOT NULL,
    isbn         VARCHAR(13)  NOT NULL,
    author_id    BIGINT REFERENCES author (id) ON DELETE CASCADE
);