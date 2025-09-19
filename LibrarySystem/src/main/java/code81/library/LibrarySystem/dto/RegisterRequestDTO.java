package code81.library.LibrarySystem.dto;


import code81.library.LibrarySystem.entity.StaffRole;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class RegisterRequestDTO{

    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private StaffRole role;
    private boolean isActive;
}