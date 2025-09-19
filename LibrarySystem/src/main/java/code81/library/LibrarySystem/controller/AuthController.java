package code81.library.LibrarySystem.controller;

import code81.library.LibrarySystem.dto.LoginRequestDTO;
import code81.library.LibrarySystem.dto.RegisterRequestDTO;
import code81.library.LibrarySystem.dto.StaffDTO;
import code81.library.LibrarySystem.entity.Staff;
import code81.library.LibrarySystem.mapper.StaffMapper;
import code81.library.LibrarySystem.repository.StaffRepository;
import code81.library.LibrarySystem.service.StaffService;
import code81.library.LibrarySystem.security.JwtService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final StaffRepository staffRepository;
    private final StaffService userService;
    private final StaffMapper staffMapper;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, StaffRepository staffRepository, StaffService userService, StaffMapper staffMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.staffRepository = staffRepository;
        this.userService = userService;
        this.staffMapper = staffMapper;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
        authenticationManager.authenticate(authToken);

        UserDetails userDetails = staffRepository.findByEmail(loginRequestDTO.getEmail());
        String token = jwtService.generateToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);

        return ResponseEntity.ok(Map.of(
                "accessToken", token,
                "refreshToken", refreshToken
        ));
    }
    @PostMapping("/register")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<StaffDTO> register(@RequestBody RegisterRequestDTO requestDTO) {

        Staff Librarian = staffMapper.toEntity(requestDTO);
        StaffDTO savedStaff = userService.Register(Librarian);
        return new ResponseEntity<>(savedStaff, HttpStatus.CREATED);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        userService.logout(session);
        return ResponseEntity.noContent().build();
    }
}


