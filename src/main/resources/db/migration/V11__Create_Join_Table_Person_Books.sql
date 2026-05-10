CREATE TABLE IF NOT EXISTS person_books (
                                            person_id BIGINT NOT NULL,
                                            book_id INTEGER NOT NULL,

                                            PRIMARY KEY (person_id, book_id),

    CONSTRAINT fk_person_books_person
    FOREIGN KEY (person_id)
    REFERENCES person_tb(id)
    ON DELETE CASCADE,

    CONSTRAINT fk_person_books_book
    FOREIGN KEY (book_id)
    REFERENCES books(id)
    ON DELETE CASCADE
    );