package fr.iris_minerva.application.dto;

import java.time.LocalDate;

import fr.iris_minerva.core.entity.Author;

public record BookWithAuthorDto(
        String id,
        String firstName,
        String lastName,
        int age,
        LocalDate birthDate,
        LocalDate deathDate) {
    public BookWithAuthorDto(Author author) {
        this(
                author.getId(),
                author.getFirstName(),
                author.getLastName(),
                author.getAge(),
                author.getBirthDate(),
                author.getDeathDate());
    }
}
