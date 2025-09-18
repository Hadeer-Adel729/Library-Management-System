package code81.library.LibrarySystem.repository;

import code81.library.LibrarySystem.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository  extends JpaRepository<Member, Integer> {

    boolean existsByMembershipNumber(String membershipNumber);
    Optional<Member> findByMembershipNumber(String membershipNumber);
    Page<Member> findAll(Pageable pageable);
}
