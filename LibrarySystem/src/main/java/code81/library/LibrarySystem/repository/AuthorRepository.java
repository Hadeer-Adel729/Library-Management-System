package code81.library.LibrarySystem.repository;

import code81.library.LibrarySystem.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findAuthorById(Integer id);
    boolean existsByNameContainingIgnoreCase(String name);
}
