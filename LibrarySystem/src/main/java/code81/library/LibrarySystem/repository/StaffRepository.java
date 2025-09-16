package code81.library.LibrarySystem.repository;

import code81.library.LibrarySystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<User, Integer> {
}
