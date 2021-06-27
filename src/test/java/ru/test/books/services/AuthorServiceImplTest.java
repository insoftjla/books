package ru.test.books.services;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.test.books.dto.AuthorDto;
import ru.test.books.model.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AuthorServiceImplTest extends IntegrationDaoTest {

    @Autowired
    private AuthorService authorService;

    @Test
    void whenFindAuthorByName_thenAuthorShouldNotByEmpty() {
        AuthorDto author = authorService.findAuthorByName("Robert C. Martin");

        assertNotNull(author);
    }

    @Test
    void givenAuthorNameAndTwoBooks_whenSaveAuthor_thenSavedAuthorShouldNotByNullAndBooksCountTwo() {
        final String authorName = "Herbert Schildt";
        Set<Book> books = Stream.of(
                new Book("Java: The Complete Reference, Eleventh Edition"),
                new Book("Java: A Beginner's Guide, Eighth Edition"))
                .collect(Collectors.toSet());

        AuthorDto savedAuthor = authorService.saveAuthorWithBooks(authorName, books);

        assertNotNull(savedAuthor);
        assertEquals(2, savedAuthor.getBooks().size());
    }
}