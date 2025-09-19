package code81.library.LibrarySystem.repository;

import code81.library.LibrarySystem.entity.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StaffRepository extends JpaRepository<Staff, Integer> {
	Staff findByEmail(String email);
	Staff findByid(Integer id);
	boolean existsByEmail(String email);
    boolean existsByphone(String phone);

	@Override
	Page<Staff> findAll(Pageable pageable);
}
