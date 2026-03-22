CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    author VARCHAR(150),
    launch_date TIMESTAMP NOT NULL,
    price NUMERIC(10,2) NOT NULL,
    title VARCHAR(150)
);