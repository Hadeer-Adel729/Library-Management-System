package code81.library.LibrarySystem.repository;

import code81.library.LibrarySystem.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {


}
