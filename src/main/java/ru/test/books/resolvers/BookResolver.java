package ru.test.books.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Component;
import ru.test.books.dto.BookDto;
import ru.test.books.model.Author;
import ru.test.books.services.BookService;

@Component
public class BookResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

    private final BookService bookService;

    public BookResolver(BookService bookService) {
        this.bookService = bookService;
    }

    public List<BookDto> getAllBooks() {
        List<BookDto> result = bookService.findAllBooks();
        return result;
    }

    public List<BookDto> getBooksByAuthor(Author author) {
        List<BookDto> result = bookService.findBooksByAuthor(author);
        return result;
    }

    public BookDto saveBook(String title, Set<Author> authors) {
        return bookService.saveBookWithAuthors(title, authors);
    }
}
