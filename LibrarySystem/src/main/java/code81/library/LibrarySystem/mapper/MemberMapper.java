package code81.library.LibrarySystem.mapper;


import code81.library.LibrarySystem.dto.CreateMemberDTO;
import code81.library.LibrarySystem.dto.MemberDTO;
import code81.library.LibrarySystem.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public MemberDTO toDto(Member member) {

            MemberDTO dto = new MemberDTO();
            dto.setAddress(member.getAddress());
            dto.setEmail(member.getEmail());
            dto.setPhone(member.getPhone());
            dto.setName(member.getName());
            dto.setMembershipNumber(member.getMembershipNumber());
            return dto;
        }
        public Member toEntity(MemberDTO dto) {
            Member member = new Member();
            member.setAddress(dto.getAddress());
            member.setEmail(dto.getEmail());
            // member.setPassword(dto.getPassword());
            member.setPhone(dto.getPhone());
            member.setName(dto.getName());
            member.setMembershipNumber(dto.getMembershipNumber());
            return member;
        }
    public Member toEntity(CreateMemberDTO dto) {
        Member member = new Member();
        member.setAddress(dto.getAddress());
        member.setEmail(dto.getEmail());
         member.setPassword(dto.getPassword());
        member.setPhone(dto.getPhone());
        member.setName(dto.getName());
        member.setMembershipNumber(dto.getMembershipNumber());
        return member;
    }


    }

