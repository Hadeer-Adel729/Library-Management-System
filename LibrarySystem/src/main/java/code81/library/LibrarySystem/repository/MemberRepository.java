package code81.library.LibrarySystem.repository;

import code81.library.LibrarySystem.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository  extends JpaRepository<Member, Integer> {
}
