package code81.library.LibrarySystem.service.impl;

import code81.library.LibrarySystem.dto.BorrowRequestDTO;
import code81.library.LibrarySystem.dto.ReturnRequestDTO;
import code81.library.LibrarySystem.dto.TransactionDTO;
import code81.library.LibrarySystem.entity.Book;
import code81.library.LibrarySystem.entity.Member;
import code81.library.LibrarySystem.entity.Transaction;
import code81.library.LibrarySystem.entity.TransactionStatus;
import code81.library.LibrarySystem.exception.BookNotFoundException;
import code81.library.LibrarySystem.exception.InvalidCopiesException;
import code81.library.LibrarySystem.exception.MemberNotFoundException;
import code81.library.LibrarySystem.mapper.TransactionMapper;
import code81.library.LibrarySystem.repository.BookRepository;
import code81.library.LibrarySystem.repository.MemberRepository;
import code81.library.LibrarySystem.repository.TransactionRepository;
import code81.library.LibrarySystem.service.TransactionService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public Page<TransactionDTO> getAllTransactions(Pageable pageable) {
        return transactionRepository.findAll(pageable).map(transactionMapper::toDto);
    }

    @Override
    public Page<TransactionDTO> getTransactionByStatus(TransactionStatus status, Pageable pageable) {
        return transactionRepository.findByStatus(status , pageable).map(transactionMapper::toDto);
    }

    @Override
    public TransactionDTO getTransactionByID(Integer ID) {
        return transactionRepository.findById(ID).map(transactionMapper::toDto).orElse(null);
    }

    @Override
    @Transactional
    public TransactionDTO borrowBook(BorrowRequestDTO borrowRequestDTO) {
        String bookISBN = borrowRequestDTO.getBookISBN();
        String memberNumber = borrowRequestDTO.getMemberNumber();
        Integer loanDays = borrowRequestDTO.getLoanDays();

        Book book = bookRepository.findByisbn(bookISBN)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + bookISBN));
        Member member = memberRepository.findByMembershipNumber(memberNumber)
                .orElseThrow(() -> new MemberNotFoundException("Member with number "+memberNumber+" not found"));

        Transaction transaction = new Transaction();

        if(book != null && member != null) {
            if(book.getAvailableCopies() > 0){
                // decrement availableCopies
                book.setAvailableCopies(book.getAvailableCopies() - 1);
                transaction.setBook(book);
                transaction.setMember(member);
                // status BORROWED.
                transaction.setStatus(TransactionStatus.BORROWED);
                // set issueDate=now
                transaction.setIssueDate(LocalDate.now());
                //dueDate=now+loanPeriod
                transaction.setDueDate(LocalDate.now().plusDays(loanDays));
            }else{
                throw new InvalidCopiesException("Copies not enough");
            }
        }
        return transactionMapper.toDto(transactionRepository.save(transaction));
    }

    @Override
    @Transactional
    public TransactionDTO returnBook(ReturnRequestDTO returnRequestDTO) {
        //transaction exists and is BORROWED; set returnDate=now;increment availableCopies; status RETURNED.
        if(!memberRepository.existsByMembershipNumber(returnRequestDTO.getMemberNumber())) {
            throw new MemberNotFoundException("Member with Number "+returnRequestDTO.getMemberNumber()+" not found");
        }
        Transaction transaction = transactionRepository.findById(returnRequestDTO.getTransactionID()).orElse(null);
        if(transaction == null) {
            return null;
        }
        if (transactionRepository.findStatusById(returnRequestDTO.getTransactionID()) == TransactionStatus.BORROWED) {
            transaction.setStatus(TransactionStatus.RETURNED);
            transaction.setReturnDate(LocalDate.now());
            Book book = transaction.getBook();
            book.setAvailableCopies(book.getAvailableCopies() + 1);
        }
        return transactionMapper.toDto(transactionRepository.save(transaction));
    }
}