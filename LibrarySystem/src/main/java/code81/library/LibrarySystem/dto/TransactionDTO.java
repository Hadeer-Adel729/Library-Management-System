package code81.library.LibrarySystem.dto;

import java.time.LocalDate;

import code81.library.LibrarySystem.entity.Book;
import code81.library.LibrarySystem.entity.TransactionStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionDTO {
    private Integer id;
    private Integer bookId;
    private Integer memberId;
    private Integer issuedById;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private TransactionStatus status;

}
