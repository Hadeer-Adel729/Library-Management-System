package code81.library.LibrarySystem.controller;

import code81.library.LibrarySystem.dto.LoginRequestDTO;
import code81.library.LibrarySystem.dto.MemberDTO;
import code81.library.LibrarySystem.dto.RegisterRequestDTO;
import code81.library.LibrarySystem.dto.StaffDTO;
import code81.library.LibrarySystem.entity.Member;
import code81.library.LibrarySystem.entity.Staff;
import code81.library.LibrarySystem.entity.User;
import code81.library.LibrarySystem.mapper.StaffMapper;
import code81.library.LibrarySystem.service.StaffService;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/staff")
public class StaffController {

	private final StaffService userService;

	public StaffController(StaffService userService) {
		this.userService = userService;
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Page<StaffDTO>> getAllUsers(Pageable pageable) {
		return ResponseEntity.ok(userService.getAllUsers(pageable));
	}

    @GetMapping("/users/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<StaffDTO> getUserById(@PathVariable Integer id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
