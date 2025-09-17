package code81.library.LibrarySystem.service;

import code81.library.LibrarySystem.dto.CategoryDTO;
import code81.library.LibrarySystem.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();
    Optional<CategoryDTO> findCateoryByID(Integer categoryID);
    Optional<CategoryDTO> findCateoryByName(String name);
    
    Category addCategory(Category category);
    Category updateCategory(Category category);
    void deleteCategory(int id);
}
