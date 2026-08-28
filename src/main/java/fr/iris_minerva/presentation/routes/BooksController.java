package fr.iris_minerva.presentation.routes;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.iris_minerva.application.dto.BookDto;
import fr.iris_minerva.core.entity.Book;
import fr.iris_minerva.domain.usecases.BookUsecases;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/books")
public class BooksController {

    // Injected dependency
    private final BookUsecases bookUsecases;

    /**
     * Inject bookUsecases dependency
     */
    public BooksController(BookUsecases bookUsecasesInjection) {
        this.bookUsecases = bookUsecasesInjection;
    }
    

    @GetMapping
    public List<BookDto> getAllBooks() {
        return bookUsecases.getAllBooks();
    }

    @GetMapping(params = "id")
    public ResponseEntity<BookDto> getBookById(@RequestParam String id) {
        return bookUsecases.getBookById(id);
    }

    @PostMapping
    public ResponseEntity<String> postNewBook(@RequestBody Book newBook) {
        return bookUsecases.createBook(newBook);
    }

    @PutMapping(path = "/edit")
    public ResponseEntity<String> editBookById(@RequestBody Book book) {
        return bookUsecases.editBook(book);
    }

    @DeleteMapping(params = "bookId")
    public ResponseEntity<String> deleteSelectedBook(@RequestParam String bookId) {
        return bookUsecases.deleteBook(bookId);
    }
}
