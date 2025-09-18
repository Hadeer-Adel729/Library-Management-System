package code81.library.LibrarySystem.service;


import code81.library.LibrarySystem.dto.MemberDTO;
import code81.library.LibrarySystem.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface MemberService {

    Page<MemberDTO> getAllMembers(Pageable pageable);
    Optional<MemberDTO> getMemberByNumber(String membershipNumber);
    MemberDTO createMember(Member member);
    MemberDTO updateMember(String membershipNumber , MemberDTO memberDTO);
    void deleteMember(Integer id);

}
