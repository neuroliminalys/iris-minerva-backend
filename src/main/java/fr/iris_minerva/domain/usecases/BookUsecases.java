package fr.iris_minerva.domain.usecases;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import fr.iris_minerva.application.dto.BookDto;
import fr.iris_minerva.core.entity.Book;
import fr.iris_minerva.infrastructure.repository.BookRepository;

@Component
public class BookUsecases {
    // Injected dependency
    private final BookRepository bookRepo;

    /**
     * Inject bookRepository dependency
     */
    public BookUsecases(BookRepository bookRepositoryInjection) {
        this.bookRepo = bookRepositoryInjection;
    }

    BookDto bookDtoed = new BookDto(new Book());

    public List<BookDto> getAllBooks() {
        return bookRepo.findAll().stream().map((book) -> new BookDto(book)).toList();
    }

    public ResponseEntity<BookDto> getBookById(String bookId) {
        Optional<Book> foundBook = bookRepo.findById(bookId);
        if (foundBook.isPresent()) {
            return new ResponseEntity<BookDto>(new BookDto(foundBook.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> createBook(Book book) {
        Optional<Book> foundBook = bookRepo.findByTitle(book.getTitle());
        if (foundBook.isPresent()) {
            return new ResponseEntity<String>(foundBook.get().getTitle() + "already exists", HttpStatus.CONFLICT);
        }
        bookRepo.save(book);
        return new ResponseEntity<>("Successfully created", HttpStatus.CREATED);
    }

    public ResponseEntity<String> editBook(Book editedBook) {
        try {
            Optional<Book> foundBook = bookRepo.findById(editedBook.getId());
            if (foundBook.isPresent()) {
                bookRepo.save(editedBook);
                return new ResponseEntity<String>("Successfully edited", HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_CONTENT);
        }
    }

    public ResponseEntity<String> deleteBook(String bookId) {
        Optional<Book> foundBook = bookRepo.findById(bookId);
        if (foundBook.isPresent()) {
            bookRepo.delete(foundBook.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
