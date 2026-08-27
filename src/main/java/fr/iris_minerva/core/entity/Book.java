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
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Getter
    private String id;

    @Nonnull
    @Column(nullable = false)
    @Getter
    @Setter
    private String title;

    @Nullable
    @Column(nullable = true)
    @Getter
    @Setter
    private String category;

    @Nullable
    @Column(nullable = true)
    @Getter
    @Setter
    private int publicationYear;

    @ManyToMany
    @Getter
    @Setter
    private List<Author> authors = new ArrayList<>();
}
