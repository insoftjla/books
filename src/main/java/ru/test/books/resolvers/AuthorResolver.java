package ru.test.books.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import java.util.Set;
import org.springframework.stereotype.Component;
import ru.test.books.dto.AuthorDto;
import ru.test.books.model.Book;
import ru.test.books.services.AuthorService;

@Component
public class AuthorResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

    private final AuthorService authorService;

    public AuthorResolver(AuthorService authorService) {
        this.authorService = authorService;
    }

    public AuthorDto getAuthor(String name) {
        return authorService.findAuthorByName(name);
    }

    public AuthorDto saveAuthor(String name, Set<Book> books) {
        return authorService.saveAuthorWithBooks(name, books);
    }
}
