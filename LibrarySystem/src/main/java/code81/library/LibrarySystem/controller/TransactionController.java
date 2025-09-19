package code81.library.LibrarySystem.controller;


import code81.library.LibrarySystem.dto.BorrowRequestDTO;
import code81.library.LibrarySystem.dto.ReturnRequestDTO;
import code81.library.LibrarySystem.dto.TransactionDTO;
import code81.library.LibrarySystem.entity.TransactionStatus;
import code81.library.LibrarySystem.repository.TransactionRepository;
import code81.library.LibrarySystem.service.TransactionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    TransactionRepository transactionRepository;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<Page<TransactionDTO>> getAllTransactions(@PageableDefault(size = 10) Pageable pageable) {
            return ResponseEntity.ok(transactionService.getAllTransactions(pageable));
    }

    @GetMapping("/{id}")
    public TransactionDTO getTransactionByID(@PathVariable Integer id) {
            return transactionService.getTransactionByID(id);
    }
    @GetMapping("/status/{status}")
    public ResponseEntity<Page<TransactionDTO>> getTransactionByStatus(@PathVariable TransactionStatus status , Pageable pageable) {
        return ResponseEntity.ok(transactionService.getTransactionByStatus(status,pageable));
    }
    @PatchMapping("/borrowBook")
    public ResponseEntity<TransactionDTO> BorrowBook(@RequestBody BorrowRequestDTO borrowRequestDTO){
        return ResponseEntity.ok(transactionService.borrowBook(borrowRequestDTO));
    }

    @PutMapping("/returnBook")
    public ResponseEntity<TransactionDTO> ReturnBook(@RequestBody ReturnRequestDTO returnRequestDTO){
        return ResponseEntity.ok(transactionService.returnBook(returnRequestDTO));
    }

}
