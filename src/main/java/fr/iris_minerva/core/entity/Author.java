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
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Getter
    private String id;

    @Nonnull
    @Column(nullable = false)
    @Getter
    @Setter
    private String firstName;

    @Nullable
    @Column(nullable = true)
    @Getter
    @Setter
    private String lastName;
    
    @Nullable
    @Column(nullable = true)
    @Getter
    @Setter
    private int age;

    @Nullable
    @Column(nullable = true)
    @Getter
    @Setter
    private LocalDate birthDate;

    @Nullable
    @Column(nullable = true)
    @Getter
    @Setter
    private LocalDate deathDate;

    @ManyToMany(mappedBy = "authors")
    @Getter
    @Setter
    private List<Book> books = new ArrayList<>();
}
