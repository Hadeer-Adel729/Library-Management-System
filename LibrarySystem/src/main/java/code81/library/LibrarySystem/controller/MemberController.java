package code81.library.LibrarySystem.controller;

import code81.library.LibrarySystem.dto.CreateMemberDTO;
import code81.library.LibrarySystem.dto.MemberDTO;
import code81.library.LibrarySystem.entity.Member;
import code81.library.LibrarySystem.mapper.MemberMapper;
import code81.library.LibrarySystem.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;
    private final MemberMapper memberMapper;

    public MemberController(MemberService memberService, MemberMapper memberMapper) {
        this.memberService = memberService;
        this.memberMapper = memberMapper;
    }

    @GetMapping
    public ResponseEntity<Page<MemberDTO>> getAllMembers(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(memberService.getAllMembers(pageable));
    }
    @GetMapping("/{memderNumber}")
    public ResponseEntity<Optional<MemberDTO>> getMemberByNumber(@PathVariable String memderNumber) {
        return ResponseEntity.ok(memberService.getMemberByNumber(memderNumber));
    }

    @PostMapping
    public ResponseEntity<MemberDTO> CreateBook(@RequestBody CreateMemberDTO memberRequest) {
        Member member = memberMapper.toEntity(memberRequest);
        MemberDTO savedMember = memberService.createMember(member);
        return new ResponseEntity<>(savedMember, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<MemberDTO> UpdateBook(@RequestBody MemberDTO memberDTO) {
        return new ResponseEntity<>(
                memberService.updateMember(memberDTO.getMembershipNumber() , memberDTO)
                , HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id) {
        memberService.deleteMember(id);
        return new ResponseEntity<>("Deleted Successfully" ,HttpStatus.OK);
    }
}
