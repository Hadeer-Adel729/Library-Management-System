package code81.library.LibrarySystem.service;

import code81.library.LibrarySystem.dto.LoginRequestDTO;
import code81.library.LibrarySystem.dto.RegisterRequestDTO;
import code81.library.LibrarySystem.dto.StaffDTO;
import code81.library.LibrarySystem.entity.Staff;
import code81.library.LibrarySystem.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface StaffService {

    public Optional<StaffDTO> getUserByEmail(String email);
    public Optional<StaffDTO>  getUserById(Integer userId) ;

    public Page<StaffDTO> getAllUsers(Pageable pageable);

    StaffDTO login(LoginRequestDTO loginRequestDto, HttpSession session);
    void logout(HttpSession session);
    StaffDTO Register (Staff user);

    public boolean phoneExists(String phone) ;
    public boolean emailExists(String email) ;

    }
