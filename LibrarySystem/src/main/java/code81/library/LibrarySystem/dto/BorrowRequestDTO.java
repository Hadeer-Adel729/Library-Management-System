package code81.library.LibrarySystem.dto;


import lombok.*;

@Setter
@Getter
public class BorrowRequestDTO {
    
   private String bookISBN;
   private String memberNumber;
   Integer loanDays;
}
