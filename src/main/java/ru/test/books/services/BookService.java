package ru.test.books.services;

import java.util.List;
import java.util.Set;
import ru.test.books.dto.BookDto;
import ru.test.books.model.Author;

public interface BookService {
    List<BookDto> findAllBooks();

    List<BookDto> findBooksByAuthor(Author author);

    BookDto saveBookWithAuthors(String title, Set<Author> authors);
}
