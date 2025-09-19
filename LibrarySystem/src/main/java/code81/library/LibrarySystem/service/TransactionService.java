package code81.library.LibrarySystem.service;


import code81.library.LibrarySystem.dto.BorrowRequestDTO;
import code81.library.LibrarySystem.dto.ReturnRequestDTO;
import code81.library.LibrarySystem.entity.TransactionStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import code81.library.LibrarySystem.dto.TransactionDTO;

public interface TransactionService {

    Page<TransactionDTO> getAllTransactions(Pageable pageable);
    Page<TransactionDTO> getTransactionByStatus(TransactionStatus status , Pageable pageable);
    TransactionDTO getTransactionByID(Integer ID);

    TransactionDTO borrowBook(BorrowRequestDTO borrowRequestDTO);
    TransactionDTO returnBook(ReturnRequestDTO returnRequestDTO);
}
