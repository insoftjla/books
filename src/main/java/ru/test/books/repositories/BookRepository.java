package ru.test.books.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.test.books.model.Author;
import ru.test.books.model.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findAllByAuthors(Author author);
    Optional<Book> findByTitle(String title);
}
