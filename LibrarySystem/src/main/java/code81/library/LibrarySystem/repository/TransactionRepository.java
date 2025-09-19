package code81.library.LibrarySystem.repository;

import code81.library.LibrarySystem.entity.Transaction;
import code81.library.LibrarySystem.entity.TransactionStatus;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Override
    Page<Transaction> findAll(Pageable pageable);
    Page<Transaction> findByStatus(TransactionStatus status , Pageable pageable);

    @Query("select t.status from Transaction t where t.id = :id")
    TransactionStatus findStatusById(@Param("id") Integer id);
    Optional<Transaction> findById(Integer id);
}
