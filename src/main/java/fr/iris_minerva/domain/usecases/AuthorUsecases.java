package fr.iris_minerva.domain.usecases;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import fr.iris_minerva.application.dto.AuthorDto;
import fr.iris_minerva.core.entity.Author;
import fr.iris_minerva.infrastructure.repository.AuthorRepository;

@Component
public class AuthorUsecases {
    private AuthorRepository authorRepo;

    public AuthorUsecases(AuthorRepository authRepoInjected) {
        this.authorRepo = authRepoInjected;
    }

    public ResponseEntity<String> createAuthor(Author author) {
        try {
            final String firstname = author.getFirstName();
            final String lastname = author.getLastName();
            if (authorRepo.findByFirstNameAndLastName(firstname, lastname).isPresent()) {
                return new ResponseEntity<String>(firstname + " " + lastname + " already exists", HttpStatus.CONFLICT);
            }
            authorRepo.save(author);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_CONTENT);
        }
    }

    public ResponseEntity<List<AuthorDto>> getAllAuthors() {
        return new ResponseEntity<List<AuthorDto>>(
                authorRepo.findAll().stream().map((author) -> new AuthorDto(author)).toList(), HttpStatus.OK);
    }

    public ResponseEntity<AuthorDto> getAuthorById(String id) {
        final Optional<Author> author = authorRepo.findById(id);
        if (author.isPresent()) {
            return new ResponseEntity<AuthorDto>(new AuthorDto(author.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> editAuthorInfos(Author editedAuthor) {
        // Force the implementation of a UUID to avoid new author creation
        try {
            Optional<Author> foundAuthor = authorRepo.findById(editedAuthor.getId());
            if (foundAuthor.isPresent()) {
                authorRepo.save(editedAuthor);
                return new ResponseEntity<String>("Successfully edited", HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_CONTENT);
        }
    }

    public ResponseEntity<String> deleteAuthor(String authorId) {
        final Optional<Author> author = authorRepo.findById(authorId);
        if (author.isPresent()) {
            authorRepo.delete(author.get());
            return new ResponseEntity<>("Successfully deleted", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
