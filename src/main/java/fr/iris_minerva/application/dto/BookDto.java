package fr.iris_minerva.application.dto;

import java.util.List;

import fr.iris_minerva.core.entity.Book;

public record BookDto(
        String id,
        String title,
        String category,
        int publicationYear,
        List<BookWithAuthorDto> authors) {
    public BookDto(Book book) {
        this(
                book.getId(),
                book.getTitle(),
                book.getCategory(),
                book.getPublicationYear(),
                book.getAuthors().stream().map(BookWithAuthorDto::new).toList()
            );
    }
}
