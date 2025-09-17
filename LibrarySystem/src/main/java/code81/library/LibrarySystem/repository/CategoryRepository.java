package code81.library.LibrarySystem.repository;

import code81.library.LibrarySystem.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository  extends JpaRepository<Category, Integer> {

    Category findCateoryByid(Integer categoryID);
    boolean existsByNameContainingIgnoreCase(String name);
    Category findByNameContainingIgnoreCase(String name);
    boolean existsByNameIgnoreCase(String name);
    Category findByNameIgnoreCase(String name);
    List<Category> findAllByNameContainingIgnoreCase(String name);

}
