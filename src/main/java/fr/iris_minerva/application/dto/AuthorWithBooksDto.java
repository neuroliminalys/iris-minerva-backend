package fr.iris_minerva.application.dto;

import fr.iris_minerva.core.entity.Book;

public record AuthorWithBooksDto(
        String id,
        String title,
        String category,
        int publicationYear) {
    public AuthorWithBooksDto(Book book) {
        this(
                book.getId(),
                book.getTitle(),
                book.getCategory(),
                book.getPublicationYear());
    }
}
