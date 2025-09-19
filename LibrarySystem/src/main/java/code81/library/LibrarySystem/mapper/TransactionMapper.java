package code81.library.LibrarySystem.mapper;

import org.springframework.stereotype.Component;

import code81.library.LibrarySystem.dto.TransactionDTO;
import code81.library.LibrarySystem.entity.Book;
import code81.library.LibrarySystem.entity.Member;
import code81.library.LibrarySystem.entity.Staff;
import code81.library.LibrarySystem.entity.Transaction;

@Component
public class TransactionMapper {
    
    public TransactionDTO toDto(Transaction transaction) {
        TransactionDTO dto = new TransactionDTO();
        dto.setId(transaction.getId());
        dto.setBookId(transaction.getBook().getId());
        dto.setMemberId(transaction.getMember().getId());
        dto.setIssueDate(transaction.getIssueDate());
        dto.setDueDate(transaction.getDueDate());
        dto.setReturnDate(transaction.getReturnDate());
        dto.setStatus(transaction.getStatus());
        
        return dto;
    }

    public Transaction toEntity(TransactionDTO dto) {
        Transaction transaction = new Transaction();
        transaction.setId(dto.getId());

        Book book = new Book();
        book.setId(dto.getBookId());
        transaction.setBook(book);

        Member member = new Member();
        member.setId(dto.getMemberId());
        transaction.setMember(member);

        return transaction;
    }
}
