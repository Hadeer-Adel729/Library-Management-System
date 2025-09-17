package code81.library.LibrarySystem.repository;

import code81.library.LibrarySystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
