package code81.library.LibrarySystem.controller;

import code81.library.LibrarySystem.dto.BookDTO;
import code81.library.LibrarySystem.entity.Book;
import code81.library.LibrarySystem.exception.BookNotFoundException;
import code81.library.LibrarySystem.mapper.BookMapper;
import code81.library.LibrarySystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;
    BookMapper bookMapper;


    @Autowired
    public BookController(BookService bookService , BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }
    @GetMapping
    public ResponseEntity<Page<BookDTO>> getAllBooks(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(bookService.getAllBooks(pageable));
    }

    @GetMapping("/{isbn}")
    public BookDTO getBookByISBN(@PathVariable String isbn) {
        return bookService.getBookByISBN(isbn)
                .orElseThrow(() -> new BookNotFoundException("Book not found with ISBN " + isbn));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BookDTO> CreateBook(@RequestBody BookDTO bookRequest) {
        Book book = bookMapper.toEntity(bookRequest);
        BookDTO savedBook = bookService.addBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BookDTO> UpdateBook(@RequestBody BookDTO book) {
        return new ResponseEntity<>(
                bookService.updateBook(book.getIsbn() , book)
                , HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>("Deleted Successfully" ,HttpStatus.OK);
    }

    @GetMapping("/filterByAuthor")
    public ResponseEntity<Page<BookDTO>> getAllBooksByAuthor(@RequestParam String author, @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(bookService.searchBookByAuthor(author, pageable));
    }
    @GetMapping("/filterByPublisher")
    public ResponseEntity<Page<BookDTO>> getAllBooksByPublisher(@RequestParam String publisher, @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(bookService.searchBookByPublisher(publisher, pageable));
    }
    @GetMapping("/filterByTitle")
    public ResponseEntity<BookDTO> getAllBooksByTitle(@RequestParam String title) {
        return ResponseEntity.ok(
                bookService.getBookByTitle(title)
                        .orElseThrow(() -> new BookNotFoundException("Book not found with title " + title))
        );
    }
    @GetMapping("/filterByCategory")
    public ResponseEntity<Page<BookDTO>> getAllBooksByCategory(@RequestParam String category, @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(
                bookService.searchBookByCategory(category, pageable));
    }



}