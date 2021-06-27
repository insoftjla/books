INSERT INTO authors (id, name)
VALUES (1, 'Robert C. Martin');


INSERT INTO books (id, title)
VALUES
    (1, 'Clean Code'),
    (2, 'Clean Architecture'),
    (3, 'The Clean Coder');

INSERT INTO books_authors (books_id, authors_id)
VALUES
    (1, 1),
    (2, 1),
    (3, 1);

SELECT setval('authors_id_seq', (SELECT MAX(id) FROM authors));
SELECT setval('books_id_seq', (SELECT MAX(id) FROM books));
