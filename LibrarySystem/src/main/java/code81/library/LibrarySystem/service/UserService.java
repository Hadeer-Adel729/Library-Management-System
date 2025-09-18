package code81.library.LibrarySystem.service;

import code81.library.LibrarySystem.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public Optional<User> getUserByEmail(String email);
    public Optional<User>  getUserById(int userId) ;

    public List<User> getAllUsers();

    //public boolean updateProfile(UserDTO user);


    /************************ AuthService (login , logout) ***************/
//    UserDTO login(LoginRequestDTO loginRequestDto, HttpSession session);
//    void logout(HttpSession session);
//    User Register (User user);
}
