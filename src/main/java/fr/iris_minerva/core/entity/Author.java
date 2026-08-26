package fr.iris_minerva.core.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Nonnull
    @Column(nullable = false)
    private String firstName;

    @Nullable
    @Column(nullable = true)
    private String lastName;
    
    @Nullable
    @Column(nullable = true)
    private int age;

    @Nullable
    @Column(nullable = true)
    private LocalDate birthDate;

    @Nullable
    @Column(nullable = true)
    private LocalDate deathDate;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books = new ArrayList<>();
}
