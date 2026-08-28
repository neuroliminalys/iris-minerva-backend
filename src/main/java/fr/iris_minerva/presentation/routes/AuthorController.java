package fr.iris_minerva.presentation.routes;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.iris_minerva.application.dto.AuthorDto;
import fr.iris_minerva.core.entity.Author;
import fr.iris_minerva.domain.usecases.AuthorUsecases;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorUsecases authorUsecases;

    public AuthorController(AuthorUsecases authorUsecasesInjected) {
        this.authorUsecases = authorUsecasesInjected;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createAuthor(@RequestBody Author author) {
        return authorUsecases.createAuthor(author);
    }

    @GetMapping()
    public ResponseEntity<List<AuthorDto>> getAllAuthord() {
        return authorUsecases.getAllAuthors();
    }

    @GetMapping(params = "authorId")
    public ResponseEntity<AuthorDto> getAuthorById(@RequestParam String authorId) {
        return authorUsecases.getAuthorById(authorId);
    }

    @PutMapping(path = "/edit")
    public ResponseEntity<String> editAuthorById(@RequestBody Author author) {
        return authorUsecases.editAuthorInfos(author);
    }

    @DeleteMapping(path = "/delete", params = "authorId")
    public ResponseEntity<String> deleteAuthorById(@RequestParam String authorId) {
        return authorUsecases.deleteAuthor(authorId);
    }

}
