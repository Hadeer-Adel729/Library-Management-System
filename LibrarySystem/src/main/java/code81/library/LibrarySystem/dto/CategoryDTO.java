package code81.library.LibrarySystem.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Setter
@Getter
public class CategoryDTO {
    private Integer id;
    private String name;
    private LocalDateTime createdAt;

}
