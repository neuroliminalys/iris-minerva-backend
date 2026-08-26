package fr.iris_minerva.core.entity;

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
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Nonnull
    @Column(nullable = false)
    private String title;

    @Nullable
    @Column(nullable = true)
    private String category;

    @Nullable
    @Column(nullable = true)
    private int publicationYear;

    @ManyToMany
    private List<Author> authors = new ArrayList<>();
}
