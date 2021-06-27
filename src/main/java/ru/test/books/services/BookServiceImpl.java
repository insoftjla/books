package ru.test.books.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.test.books.dto.BookDto;
import ru.test.books.model.Author;
import ru.test.books.model.Book;
import ru.test.books.repositories.AuthorRepository;
import ru.test.books.repositories.BookRepository;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> findAllBooks() {
        return BookDto.toDtos((List<Book>) bookRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDto> findBooksByAuthor(Author author) {
        return BookDto.toDtos(bookRepository.findAllByAuthors(author));
    }

    @Override
    @Transactional
    public BookDto saveBookWithAuthors(String title, Set<Author> authors) {
        Optional<Book> optionalBook = bookRepository.findByTitle(title);
        Book book;
        if (optionalBook.isPresent()) {
            book = optionalBook.get();
        } else {
            book = new Book();
            book.setTitle(title);
            book = bookRepository.save(book);
        }
        book.addAuthors(mergeAuthorsFromDb(authors));
        return BookDto.toDto(bookRepository.save(book));
    }

    private Set<Author> mergeAuthorsFromDb(Set<Author> authors) {
        return authors.stream()
                .map(author -> authorRepository.findByName(author.getName()).orElse(author))
                .collect(Collectors.toSet());
    }
}
