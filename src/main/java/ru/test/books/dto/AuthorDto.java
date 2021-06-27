package ru.test.books.dto;

import java.util.Set;
import java.util.stream.Collectors;
import ru.test.books.model.Author;
import ru.test.books.model.Book;

public class AuthorDto {
    private Long id;
    private String name;
    private Set<BookDto> books;

    public AuthorDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static AuthorDto toDto(Author author) {
        if (author == null) {
            return null;
        }
        AuthorDto result = new AuthorDto(author.getId(), author.getName());
        result.setBooks(getBookDtos(author.getBooks()));
        return result;
    }

    private static Set<BookDto> getBookDtos(Set<Book> books) {
        return books.stream()
                .map(book -> new BookDto(book.getId(), book.getTitle()))
                .collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<BookDto> getBooks() {
        return books;
    }

    public void setBooks(Set<BookDto> books) {
        this.books = books;
    }
}
