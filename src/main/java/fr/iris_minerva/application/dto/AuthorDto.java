package fr.iris_minerva.application.dto;

import java.time.LocalDate;
import java.util.List;

import fr.iris_minerva.core.entity.Author;

public record AuthorDto(
        String id,
        String firstName,
        String lastName,
        int age,
        LocalDate birthDate,
        LocalDate deathDate,
        List<AuthorWithBooksDto> books
    ) {
    public AuthorDto(Author author) {
        this(
            author.getId(),
            author.getFirstName(),
            author.getLastName(),
            author.getAge(),
            author.getBirthDate(),
            author.getDeathDate(),
            author.getBooks().stream().map(AuthorWithBooksDto::new).toList()
        );
    }
}
