package fr.iris_minerva.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iris_minerva.core.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, String> {
    public Optional<Author> findByLastName(String lastname);
    public Optional<Author> findByFirstName(String firstname);

    public Optional<Author> findByFirstNameAndLastName(String firstname, String lastname);
    
}
