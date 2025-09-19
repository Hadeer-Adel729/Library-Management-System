package code81.library.LibrarySystem.service;

import code81.library.LibrarySystem.dto.LoginRequestDTO;
import code81.library.LibrarySystem.dto.UserDTO;
import code81.library.LibrarySystem.entity.User;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public Optional<User> getUserByEmail(String email);
    public Optional<User>  getUserById(int userId) ;

    public List<User> getAllUsers();

    UserDTO login(LoginRequestDTO loginRequestDto, HttpSession session);
    void logout(HttpSession session);
    User Register (User user);
}
