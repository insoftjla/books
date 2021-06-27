package ru.test.books.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.test.books.dto.AuthorDto;
import ru.test.books.model.Author;
import ru.test.books.model.Book;
import ru.test.books.repositories.AuthorRepository;
import ru.test.books.repositories.BookRepository;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public AuthorDto findAuthorByName(String name) {
        return AuthorDto.toDto(authorRepository.findByName(name).orElse(null));
    }

    @Override
    @Transactional
    public AuthorDto saveAuthorWithBooks(String name, Set<Book> books) {
        Optional<Author> optionalAuthor = authorRepository.findByName(name);
        Author author;
        if (optionalAuthor.isPresent()) {
            author = optionalAuthor.get();
        } else {
            author = new Author();
            author.setName(name);
            author = authorRepository.save(author);
        }
        author.setBooks(mergeBooksFromDb(books));
        return AuthorDto.toDto(authorRepository.save(author));
    }

    private Set<Book> mergeBooksFromDb(Set<Book> books) {
        return books.stream()
                .map(book -> bookRepository.findByTitle(book.getTitle()).orElse(book))
                .collect(Collectors.toSet());
    }
}
