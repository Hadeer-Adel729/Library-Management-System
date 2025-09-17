package code81.library.LibrarySystem.service;


import code81.library.LibrarySystem.dto.BookDTO;
import code81.library.LibrarySystem.entity.Book;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.Optional;

public interface BookService {

    Page<BookDTO> getAllBooks(Pageable pageable) ;
    Page<BookDTO> searchBookByAuthor(String author , Pageable pageable);
    Page<BookDTO> searchBookByPublisher(String publisher , Pageable pageable);
    Page<BookDTO> searchBookByCategory(String category , Pageable pageable);
    Optional<BookDTO> getBookByISBN(String isbn);
    Optional<BookDTO> getBookByTitle(String title);

    BookDTO addBook(Book book);

    BookDTO updateBook(String  isbn, BookDTO bookUpdate);
    void deleteBook(Integer id);


}
