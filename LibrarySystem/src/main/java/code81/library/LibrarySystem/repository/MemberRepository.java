package code81.library.LibrarySystem.repository;

import code81.library.LibrarySystem.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository  extends JpaRepository<Member, Integer> {

    Member findMemberById(Integer id);
    boolean existsByMembershipNumber(String membershipNumber);
    Member findByMembershipNumber(String membershipNumber);
    List<Member> findAllByMembershipNumberContainingIgnoreCase(String membershipNumber);
}
