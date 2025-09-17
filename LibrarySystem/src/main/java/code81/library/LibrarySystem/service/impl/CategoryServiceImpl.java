package code81.library.LibrarySystem.service.impl;

import code81.library.LibrarySystem.dto.CategoryDTO;
import code81.library.LibrarySystem.entity.Category;
import code81.library.LibrarySystem.mapper.CategoryMapper;
import code81.library.LibrarySystem.repository.CategoryRepository;
import code81.library.LibrarySystem.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream().map(CategoryMapper::toDto).collect(Collectors.toList());
    }
    @Override
    public Optional<CategoryDTO> findCateoryByID(Integer categoryID) {
        return categoryRepository.findById(categoryID).map(CategoryMapper::toDto);
    }
    @Override
    public Optional<CategoryDTO> findCateoryByName(String name) {
        // Prefer exact (case-insensitive) match first to avoid non-unique results
        Category exact = categoryRepository.findByNameIgnoreCase(name);
        if (exact != null) {
            return Optional.of(CategoryMapper.toDto(exact));
        }
        // Fallback: if no exact match, return empty to avoid ambiguous matches
        return Optional.empty();
    }
    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }
    @Override
    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }
    @Override
    public void deleteCategory(int id) {
        categoryRepository.deleteById(id);
    }

}
