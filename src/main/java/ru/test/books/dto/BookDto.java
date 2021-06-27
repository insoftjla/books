package ru.test.books.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import ru.test.books.model.Author;
import ru.test.books.model.Book;

public class BookDto {
    private Long id;
    private String title;
    private Set<AuthorDto> authors;

    public BookDto(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public static BookDto toDto(Book book) {
        if (book == null) {
            return null;
        }
        BookDto result = new BookDto(book.getId(), book.getTitle());
        result.setAuthors(getAuthorsDto(book.getAuthors()));
        return result;
    }

    private static Set<AuthorDto> getAuthorsDto(Set<Author> authors) {
        return authors.stream()
                .map(author -> new AuthorDto(author.getId(), author.getName()))
                .collect(Collectors.toSet());
    }

    public static List<BookDto> toDtos(List<Book> books) {
        if (books == null || books.isEmpty()) {
            return null;
        }
        return books.stream()
                .map(BookDto::toDto)
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<AuthorDto> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorDto> authors) {
        this.authors = authors;
    }
}
