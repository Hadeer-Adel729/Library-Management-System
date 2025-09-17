package code81.library.LibrarySystem.repository;

import code81.library.LibrarySystem.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Override
    Page<Book> findAll(Pageable pageable);
    Optional<Book> findByisbn(String ISBN);
    boolean existsBytitleContainingIgnoreCase(String title);
    boolean existsByisbn(String ISBN);

    Optional<Book> findBytitleContainingIgnoreCase(String title);
    Page<Book> findByauthors_NameContainingIgnoreCase(String authorName, Pageable pageable);
    Page<Book> findBycategory_NameContainingIgnoreCase(String categoryName, Pageable pageable);
    Page<Book> findBypublishers_NameContainingIgnoreCase(String publisher, Pageable pageable);
}

