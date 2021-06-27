package ru.test.books.services;

import java.util.Set;
import ru.test.books.dto.AuthorDto;
import ru.test.books.model.Book;

public interface AuthorService {
    AuthorDto findAuthorByName(String name);

    AuthorDto saveAuthorWithBooks(String name, Set<Book> books);
}
