package ru.test.books.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.test.books.dto.BookDto;
import ru.test.books.model.Author;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceImplTest extends IntegrationDaoTest {

    @Autowired
    private BookService bookService;

    @Test
    void whenFindAllBooks_thenListShouldNotByEmpty() {
        List<BookDto> books = bookService.findAllBooks();

        assertFalse(books.isEmpty());
    }

    @Test
    void givenAuthor_whenGetBooksByAuthor_thenListShouldBySizeThree() {
        final Author author = new Author();
        author.setId(1L);
        author.setName("Robert C. Martin");

        List<BookDto> books = bookService.findBooksByAuthor(author);

        assertEquals(3, books.size());
    }

    @Test
    void givenTitleAndTwoAuthors_whenSaveBook_thenSavedBookShouldNotByNullAndAuthorsCountTwo() {
        final String title = "Head First Java";
        final Set<Author> authors = Stream.of(new Author("Kathy Sierra"), new Author("Bert Bates"))
                .collect(Collectors.toSet());

        BookDto savedBook = bookService.saveBookWithAuthors(title, authors);

        assertNotNull(savedBook);
        assertEquals(2, savedBook.getAuthors().size());
    }
}