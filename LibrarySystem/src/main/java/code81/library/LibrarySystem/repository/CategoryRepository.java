package code81.library.LibrarySystem.repository;

import code81.library.LibrarySystem.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository  extends JpaRepository<Category, Integer> {
}
