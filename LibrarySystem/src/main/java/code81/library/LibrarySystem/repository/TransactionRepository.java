package code81.library.LibrarySystem.repository;

import code81.library.LibrarySystem.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
