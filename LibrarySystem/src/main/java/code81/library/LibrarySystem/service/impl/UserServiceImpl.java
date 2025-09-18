package code81.library.LibrarySystem.service.impl;

import code81.library.LibrarySystem.entity.User;
import code81.library.LibrarySystem.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public Optional<User> getUserByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserById(int userId) {
        return Optional.empty();
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

//    @Override
//    public boolean updateProfile(UserDTO user) {
//        return false;
//    }
//
//    @Override
//    public UserDTO login(LoginRequestDTO loginRequestDto, HttpSession session) {
//        return null;
//    }
//
//    @Override
//    public void logout(HttpSession session) {
//
//    }
//
//    @Override
//    public User Register(User user) {
//        return null;
//    }
}
