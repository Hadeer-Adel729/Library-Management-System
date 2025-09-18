package code81.library.LibrarySystem.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Setter
@Getter
@Component
public class CreateMemberDTO {
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String membershipNumber;
}
