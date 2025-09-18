package code81.library.LibrarySystem.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BookDTO {

    private String categoryName;
    private String language;
    private Integer publicationYear;
    private String isbn;
    private String title;
    private String edition;
    private String summary;
    private String coverUrl;
    private Integer totalCopies;
    private Integer availableCopies;
//    private List<String> authorNames;
//    private List<String> publisherNames;
}
