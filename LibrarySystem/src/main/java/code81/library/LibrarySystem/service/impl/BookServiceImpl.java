package code81.library.LibrarySystem.service.impl;

import code81.library.LibrarySystem.dto.BookDTO;
import code81.library.LibrarySystem.entity.*;
import code81.library.LibrarySystem.exception.*;
import code81.library.LibrarySystem.mapper.BookMapper;
import code81.library.LibrarySystem.repository.*;
import code81.library.LibrarySystem.service.BookService;
import lombok.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    private final BookMapper bookMapper;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    @Override
    public Page<BookDTO> getAllBooks(Pageable pageable) {
        Page<Book> booksPage = bookRepository.findAll(pageable);
        return booksPage.map(bookMapper::toDto);
    }

    @Override
    public Page<BookDTO> searchBookByAuthor(String author, Pageable pageable) {
        if(!authorRepository.existsByNameContainingIgnoreCase(author)){
            throw new AuthorNotFoundException("Author with name " + author + " not found");
        }
        Page<Book> booksPage = bookRepository.findByauthors_NameContainingIgnoreCase(author, pageable);
        return booksPage.map(bookMapper::toDto);
    }

    @Override
    public Page<BookDTO> searchBookByPublisher(String publisher, Pageable pageable) {
        if(!publisherRepository.existsBynameContainingIgnoreCase(publisher)){
            throw new PublisherNotFoundException("Publisher with name " + publisher + " not found");
        }
        Page<Book> booksPage = bookRepository.findBypublishers_NameContainingIgnoreCase(publisher, pageable);
        return booksPage.map(bookMapper::toDto);
    }

    @Override
    public Page<BookDTO> searchBookByCategory(String category, Pageable pageable) {
        if (!categoryRepository.existsByNameContainingIgnoreCase(category)) {
            throw new CategoryNotFoundException("Category: "+ category + " not found");
        }
        Page<Book> booksPage = bookRepository.findBycategory_NameContainingIgnoreCase(category, pageable);
        return booksPage.map(bookMapper::toDto);
    }

    @Override
    public Optional<BookDTO> getBookByISBN(String isbn) {
        if(!bookRepository.existsByisbn(isbn)){
            throw new BookNotFoundException("Book with ISBN: "+ isbn + " not found");
        }
        return bookRepository.findByisbn(isbn)
                .map(bookMapper::toDto);
    }

    @Override
    public Optional<BookDTO> getBookByTitle(String title) {
        if (!bookRepository.existsBytitleContainingIgnoreCase(title)) {
            throw new BookNotFoundException("Book not found with title: " + title);
        }        
        return bookRepository.findBytitleContainingIgnoreCase(title)
                .map(bookMapper::toDto);
    }
    @Override
    @Transactional
    public BookDTO addBook(Book book) {
        // Check for duplicate ISBN
        if (bookRepository.existsByisbn(book.getIsbn())) {
            throw new BookAlreadyExistException("Book with ISBN " + book.getIsbn() + " already exists");
        }
        // Resolve category by name if provided
        if (book.getCategory() != null) {
            String categoryName = book.getCategory().getName();
            if (categoryName == null || categoryName.isBlank()) {
                book.setCategory(null);
            } else {
                Category resolved = categoryRepository.findByNameContainingIgnoreCase(categoryName);
                if (resolved == null) {
                    throw new CategoryNotFoundException("Category not found: " + categoryName);
                }
                book.setCategory(resolved);
            }
        }
        // Validate total copies
        if (book.getTotalCopies() == null || book.getTotalCopies() < 0) {
            throw new InvalidCopiesException("Total copies must be non-negative");
        }
        // Set available copies equal to total copies for new books
        book.setAvailableCopies(book.getTotalCopies());

        Book savedBook = bookRepository.save(book);
        return bookMapper.toDto(savedBook);
    }

@Override
@Transactional
public BookDTO updateBook(String isbn, BookDTO bookUpdate) {
    Book existingBook = bookRepository.findByisbn(isbn)
            .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + isbn));

    if (bookUpdate.getTitle() != null) {
        existingBook.setTitle(bookUpdate.getTitle());
    }
    if (bookUpdate.getTotalCopies() != null) {
        existingBook.setTotalCopies(bookUpdate.getTotalCopies());
    }
    if (bookUpdate.getAvailableCopies() != null) {
        existingBook.setAvailableCopies(bookUpdate.getAvailableCopies());
    }
    if(bookUpdate.getEdition() != null) {
        existingBook.setEdition(bookUpdate.getEdition());
    }
    if(bookUpdate.getCategoryName()!= null){
        existingBook.setCategory(categoryRepository.findByNameContainingIgnoreCase(bookUpdate.getCategoryName()));
    }

    Book updatedBook = bookRepository.save(existingBook);
    return bookMapper.toDto(updatedBook);
}

    @Override
    @Transactional
    public void deleteBook(Integer id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book not found with id" + id);
        }
        bookRepository.deleteById(id);
    }
}