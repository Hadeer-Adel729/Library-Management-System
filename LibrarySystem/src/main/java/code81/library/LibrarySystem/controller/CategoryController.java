package code81.library.LibrarySystem.controller;

import code81.library.LibrarySystem.dto.CategoryDTO;
import code81.library.LibrarySystem.entity.Category;
import code81.library.LibrarySystem.exception.CategoryNotFoundException;
import code81.library.LibrarySystem.mapper.CategoryMapper;
import code81.library.LibrarySystem.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Integer id) {
        return ResponseEntity.ok(
                categoryService.findCateoryByID(id)
                        .orElseThrow(() -> new CategoryNotFoundException("Category not found with id: " + id))
        );
    }

    @GetMapping("/filterByName")
    public ResponseEntity<CategoryDTO> getCategoryByName(@RequestParam String name) {
        return ResponseEntity.ok(
                categoryService.findCateoryByName(name)
                        .orElseThrow(() -> new CategoryNotFoundException("Category not found with name: " + name))
        );
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO request) {
        Category toSave = CategoryMapper.toEntity(request);
        Category saved = categoryService.addCategory(toSave);
        CategoryDTO response = CategoryMapper.toDto(saved);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO request) {
        Category toUpdate = CategoryMapper.toEntity(request);
        Category updated = categoryService.updateCategory(toUpdate);
        CategoryDTO response = CategoryMapper.toDto(updated);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }
}
