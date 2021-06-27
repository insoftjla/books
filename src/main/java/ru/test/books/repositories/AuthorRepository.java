package ru.test.books.repositories;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.test.books.model.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    Optional<Author> findByName(String name);
}
