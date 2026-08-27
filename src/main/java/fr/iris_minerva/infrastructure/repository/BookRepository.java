package fr.iris_minerva.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iris_minerva.core.entity.Book;

public interface BookRepository extends JpaRepository<Book, String> {
    public List<Book> findAll();
    public Optional<Book> findById(String id);
    public Optional<Book> findByTitle(String title);
}
