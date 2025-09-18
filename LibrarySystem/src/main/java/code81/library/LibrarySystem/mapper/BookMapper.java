package code81.library.LibrarySystem.mapper;

import code81.library.LibrarySystem.dto.BookDTO;
import code81.library.LibrarySystem.entity.Book;
import code81.library.LibrarySystem.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookDTO toDto(Book book) {
        BookDTO dto = new BookDTO();
        dto.setTitle(book.getTitle());
        dto.setIsbn(book.getIsbn());
        dto.setPublicationYear(book.getPublicationYear());
        dto.setEdition(book.getEdition());
        dto.setSummary(book.getSummary());
        dto.setCategoryName(book.getCategory().getName());
        dto.setAvailableCopies(book.getAvailableCopies());
        dto.setTotalCopies(book.getTotalCopies());
        return dto;
    }

    public Book toEntity(BookDTO dto) {

        Book book = new Book();

        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        book.setEdition(dto.getEdition());
        book.setSummary(dto.getSummary());
        if (dto.getCategoryName() != null) {
            Category category = new Category();
            category.setName(dto.getCategoryName());
            book.setCategory(category);
        } else {
            book.setCategory(null);
        }
        book.setAvailableCopies(dto.getAvailableCopies());
        book.setTotalCopies(dto.getTotalCopies());
        return book;
    }


}