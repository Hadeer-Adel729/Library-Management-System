package code81.library.LibrarySystem.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class MemberDTO {
    private String name;
    private String email;
    private String phone;
    private String address;
    private String membershipNumber;
}
