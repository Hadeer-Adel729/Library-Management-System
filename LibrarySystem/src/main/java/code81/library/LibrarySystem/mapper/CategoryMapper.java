package code81.library.LibrarySystem.mapper;

import code81.library.LibrarySystem.dto.CategoryDTO;
import code81.library.LibrarySystem.entity.Category;

public class CategoryMapper {

    public static CategoryDTO toDto(Category category) {
        if (category == null) {
            return null;
        }
        CategoryDTO dto = new CategoryDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
//        if (category.getParent() != null) {
//            dto.setParentId(category.getParent().getId());
//        }
        dto.setCreatedAt(category.getCreatedAt());
        return dto;
    }
    public static Category toEntity(CategoryDTO dto) {
        if (dto == null) {
            return null;
        }
        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());
//        if (dto.getParentId() != null) {
//            Category parentRef = new Category();
//            parentRef.setId(dto.getParentId());
//            category.setParent(parentRef);
//        }
        return category;
    }
}
