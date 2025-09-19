package code81.library.LibrarySystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class ReturnRequestDTO {
    private String bookISBN;
    private String memberNumber;
    private Integer transactionID;
}
