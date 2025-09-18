package code81.library.LibrarySystem.service.impl;

import code81.library.LibrarySystem.dto.MemberDTO;
import code81.library.LibrarySystem.entity.Member;
import code81.library.LibrarySystem.exception.MemberAlreadyExistsExcepion;
import code81.library.LibrarySystem.exception.MemberNotFoundException;
import code81.library.LibrarySystem.mapper.MemberMapper;
import code81.library.LibrarySystem.repository.MemberRepository;
import code81.library.LibrarySystem.service.MemberService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    public MemberServiceImpl(MemberRepository memberRepository, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    @Override
    public Page<MemberDTO> getAllMembers(Pageable pageable) {
        Page<Member> booksPage = memberRepository.findAll(pageable);
        return booksPage.map(memberMapper::toDto);
    }

    @Override
    public Optional<MemberDTO> getMemberByNumber(String membershipNumber) {

        if(!memberRepository.existsByMembershipNumber(membershipNumber)){
            throw new MemberNotFoundException("Member with Number: "+ membershipNumber + " not found");
        }
        return memberRepository.findByMembershipNumber(membershipNumber)
                .map(memberMapper::toDto);
    }

    @Override
    @Transactional
    public MemberDTO createMember(Member member) {
        if(memberRepository.existsByMembershipNumber(member.getMembershipNumber())){
            throw new MemberAlreadyExistsExcepion("Member with Number: "+ member.getMembershipNumber() + " already exists");
        }
        member.setMembershipNumber(member.getMembershipNumber());
        member.setName(member.getName());
        member.setEmail(member.getEmail());
        member.setPhone(member.getPhone());
        member.setAddress(member.getAddress());
        member.setPassword(member.getPassword());

        Member savedMember = memberRepository.save(member);
        return memberMapper.toDto(savedMember);
    }

    @Override
    @Transactional
    public MemberDTO updateMember(String membershipBumber , MemberDTO memberUpdate) {

        Member existingMember = memberRepository.findByMembershipNumber(membershipBumber)
                .orElseThrow(() -> new MemberNotFoundException("Member with Number: "+ memberUpdate.getMembershipNumber() + " not found"));

        if (memberUpdate.getMembershipNumber() != null) {
            existingMember.setMembershipNumber(memberUpdate.getMembershipNumber());
        }
        if (memberUpdate.getAddress() != null) {
            existingMember.setAddress(memberUpdate.getAddress());
        }
        if (memberUpdate.getEmail() != null) {
            existingMember.setEmail(memberUpdate.getEmail());
        }
        if (memberUpdate.getPhone() != null) {
            existingMember.setPhone(memberUpdate.getPhone());
        }
        if (memberUpdate.getName() != null) {
            existingMember.setName(memberUpdate.getName());
        }
        Member updatedmember= memberRepository.save(existingMember);
        return memberMapper.toDto(updatedmember);
    }

    @Override
    @Transactional
    public void deleteMember(Integer id) {
        if (!memberRepository.existsById(id)) {
            throw new MemberNotFoundException("Member not found with id: " + id);
        }
        memberRepository.deleteById(id);
    }
}
